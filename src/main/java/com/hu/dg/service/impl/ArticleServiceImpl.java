package com.hu.dg.service.impl;

import com.hu.dg.cache.SiteCache;
import com.hu.dg.domain.*;
import com.hu.dg.domain.vo.CommentVO;
import com.hu.dg.query.PageQuery;
import com.hu.dg.repository.*;
import com.hu.dg.service.ArticleService;
import com.hu.dg.type.ArticleCategory;
import com.hu.dg.type.ArticleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) ArticleServiceImpl.java 2016/06/12 10:22
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RecycleBinArticleRepository recycleBinArticleRepository;

    @Autowired
    private ArticleAboutInfoRepository aboutInfoRepository;

    @Autowired
    private FocusArticleRepository focusArticleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentAboutInfoRepository commentAboutInfoRepository;

    @Autowired
    private BrowseRecordRepository browseRecordRepository;
//    @Autowired
//    private UserEffectCommentRepository userEffectCommentRepository;

    @Autowired
    private TagRepository tagRepository;

    /**
     * 获取文章列表，及回收站的最大id
     *
     * @param article
     */
    public void edit(Article article) {
        if (article.getId() <= 0) {
            save(article);
        } else {
            Article dbArticle = articleRepository.findById(article.getId());
            if (dbArticle == null) {
                save(dbArticle);
            } else {
                dbArticle.setTitle(article.getTitle());
                dbArticle.setCategory(article.getCategory());
                dbArticle.setContent(article.getContent());
                dbArticle.setDescription(article.getDescription());
                dbArticle.setTag(article.getTag());
                dbArticle.setTime(System.currentTimeMillis());
//                dbArticle.setStatus(article.getStatus());
//                dbArticle.setOpen(article.isOpen());
                dbArticle.setImage(article.getImage());
                dbArticle.setOriginalUrl(article.getOriginalUrl());
                articleRepository.update(dbArticle);
            }
        }

    }

    private void save(Article article) {
        int maxId = articleRepository.getMaxId();
        int recycleBinArticleRepositoryMaxId = recycleBinArticleRepository.getMaxId();
        if (maxId < recycleBinArticleRepositoryMaxId) {
            maxId = recycleBinArticleRepositoryMaxId;
        }
        maxId++;
        article.setId(maxId);
        article.setTime(System.currentTimeMillis());
        article.setStatus(ArticleStatus.OPEN.name());
        articleRepository.create(article);
        createArticleAboutInfo(article.getId());
    }

    /**
     * 创建文章相关信息
     *
     * @param articleId
     */
    private ArticleAboutInfo createArticleAboutInfo(int articleId) {
        ArticleAboutInfo articleAboutInfo = new ArticleAboutInfo();
        articleAboutInfo.setArticleId(articleId);
        articleAboutInfo.setBrowseCount(1);
        articleAboutInfo.setFocusCount(0);
        articleAboutInfo.setSupportCount(0);
        articleAboutInfo.setOpposeCount(0);
        articleAboutInfo.setCommentCount(0);
        aboutInfoRepository.create(articleAboutInfo);
        return articleAboutInfo;
    }

    public Article findById(int id) {
        Article dbArticle = articleRepository.findById(id);
        if (dbArticle == null) {
            dbArticle = recycleBinArticleRepository.findById(id);
        }
        return dbArticle;
    }

    public Map<String, Object> findArticlePageMsg(int articleId, User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> backMap = new HashMap<String, Object>();
        int authorId = articleRepository.findUserIdByArticleId(articleId);
        backMap.put("articleAboutInfo", getArticleAboutInfo(articleId));
        addBrowseRecord(articleId, user, request);
        if (user != null) {
            if (user.getId() == authorId) {
                backMap.put("own", true);
            } else {
                FocusArticle focusArticle = focusArticleRepository.find(user.getId(), articleId);
                backMap.put("focusArticle", focusArticle);
            }
        }
        List<CommentVO> commentList = findCommentList(articleId);
        backMap.put("commentListVO", commentList);
//        backMap.put("article", dbArticle);

        backMap.put("authorArticleList", listAuthorArticle(articleId,authorId));
        backMap.put("hotArticleList", listHotArticle(articleId));
        backMap.put("newArticleList", listNewArticle(articleId));
        return backMap;
    }



    /**
     * 添加浏览记录
     *
     * @param articleId
     * @param user
     * @param request
     */
    private void addBrowseRecord(int articleId, User user, HttpServletRequest request) {
        BrowseRecord record = null;
        if (user != null) {
            record = browseRecordRepository.findByArticleIdAndUserId(articleId, user.getId());
            if (record == null) {
                record = browseRecordRepository.findByArticleIdAndIp(articleId, request.getRemoteAddr());
            }
        } else {
            record = browseRecordRepository.findByArticleIdAndIp(articleId, request.getRemoteAddr());
        }
        if (record == null) {
            createBrowseRecord(articleId, user, request);
        } else {
            updateBrowseRecord(record, user, request);
        }
    }

    private void createBrowseRecord(int articleId, User user, HttpServletRequest request) {
        int maxId = browseRecordRepository.getMaxId();
        maxId++;
        BrowseRecord browseRecord = new BrowseRecord();
        browseRecord.setId(maxId);
        browseRecord.setArticleId(articleId);
        if (user != null) {
            browseRecord.setUserId(user.getId());
        }
        browseRecord.setIp(request.getRemoteAddr());
        browseRecord.setTime(System.currentTimeMillis());
        browseRecordRepository.create(browseRecord);
    }

    private void updateBrowseRecord(BrowseRecord browseRecord, User user, HttpServletRequest request) {
        if (user != null) {
            browseRecord.setUserId(user.getId());
        }
        browseRecord.setIp(request.getRemoteAddr());
        browseRecord.setTime(System.currentTimeMillis());
        browseRecordRepository.update(browseRecord);
    }

    private ArticleAboutInfo getArticleAboutInfo(int articleId) {
        ArticleAboutInfo articleAboutInfo = aboutInfoRepository.findById(articleId);
        if (articleAboutInfo == null) {
            articleAboutInfo = createArticleAboutInfo(articleId);
        } else {
            articleAboutInfo.setBrowseCount(articleAboutInfo.getBrowseCount() + 1);
            updateArticleAboutInfo(articleAboutInfo);
        }
        return articleAboutInfo;
    }

    private List<Article> listAuthorArticle(int articleId,int authorId) {
        Map<String, Object> articleSearchMap = new HashMap<String, Object>();
        articleSearchMap.put("userId", authorId);
        articleSearchMap.put("articleId", articleId);
        articleSearchMap.put("start", 0);
        articleSearchMap.put("size", 3);
        return articleRepository.list(articleSearchMap);
    }




    private List<CommentVO> findCommentList(int articleId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("articleId", articleId);
        PageQuery query = new PageQuery();
        params.put("start", query.getStart());
        params.put("size", query.getSize());
        List<Comment> list = commentRepository.list(params);
        List<CommentVO> voList = new ArrayList<CommentVO>();
        for (Comment comment : list) {
            CommentVO commentVO = new CommentVO();
            commentVO.setComment(comment);
            CommentAboutInfo commentAboutInfo = commentAboutInfoRepository.findById(comment.getId());
            commentVO.setCommentAboutInfo(commentAboutInfo);
            voList.add(commentVO);
        }
        return voList;
    }

    private void updateArticleAboutInfo(ArticleAboutInfo articleAboutInfo) {
        aboutInfoRepository.update(articleAboutInfo);
    }


    public List<Article> list(Map<String, Object> params) {
        updateTag(params);
        return articleRepository.list(params);
    }

    /**
     * 关键词搜索，更新关键词浏览次数
     *
     * @param params
     */
    private void updateTag(Map<String, Object> params) {
        if (!StringUtils.isEmpty(params.get("keywords"))) {
            String keywords = (String) params.get("keywords");

            Tag tag = tagRepository.findByName(keywords);
            if (tag == null) {
                tag = new Tag();
                int maxId = tagRepository.getMaxId();
                maxId++;
                tag.setId(maxId);
                tag.setName(keywords);
                tag.setCount(1);
                tagRepository.create(tag);
            } else {
                tag.setCount(tag.getCount() + 1);
                tagRepository.update(tag);
            }
        }
    }

    public int getCount(Map<String, Object> params) {
        return articleRepository.getCount(params);
    }

    public void changeOpen(Article article, int userId) {
        Article dbArticle = articleRepository.findById(article.getId());
        if (dbArticle == null) {
            return;
        }
        if (dbArticle.getUserId() != userId) {
            return;
        }
        if (dbArticle.isOpen() == article.isOpen()) {
            return;
        }
        articleRepository.changeOpen(dbArticle.getId(), article.isOpen());
    }

    /**
     * @param articleId
     * @param userId
     */
    public void delete(int articleId, int userId) {
        Article dbArticle = articleRepository.findById(articleId);
        if (dbArticle == null) {
            return;
        }
        if (dbArticle.getUserId() != userId) {
            return;
        }
        recycleBinArticleRepository.create(dbArticle);
        articleRepository.delete(dbArticle.getId(), userId);
    }

    /**
     * 获取最热的文章
     *
     * @return
     */
    public List<Article> listHotArticle(int articleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("articleId", articleId);
        map.put("start", 0);
        map.put("size", 3);
        List<Article> list = articleRepository.listHotArticle(map);
        return list;
    }

    /**
     * 获取最新的文章
     *
     * @return
     */
    public List<Article> listNewArticle(int articleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("articleId", articleId);
        map.put("start", 0);
        map.put("size", 3);
        List<Article> list = articleRepository.list(map);
        return list;
    }
}