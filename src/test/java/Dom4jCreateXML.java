import com.hu.dg.domain.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 * @(#) Dom4jCreateXML.java 2016/03/22 17:18
 */
public class Dom4jCreateXML {

    public static void main(String[] args) {
//        createMapper(User.class,directoryPath,Arrays.asList(INSERT,FIND_BY_ID,GET_COUNT,GET_MAX_ID,LIST));
//        createMapper(Remark.class,directoryPath,Arrays.asList(INSERT));
//        createMapper(LoginLog.class,directoryPath,Arrays.asList(INSERT,LIST,GET_COUNT,GET_MAX_ID));
//        createMapper(Article.class,testDirectoryPath,Arrays.asList(INSERT,LIST,GET_COUNT,GET_MAX_ID,UPDATE));
//        createMapper(EmailSetting.class,directoryPath,Arrays.asList(INSERT,LIST,GET_COUNT,GET_MAX_ID,FIND_BY_ID,UPDATE));
//        createMapper(EmailRecord.class,directoryPath,Arrays.asList(INSERT,LIST,GET_COUNT,GET_MAX_ID,FIND_BY_ID));
//        createMapper(EmailMsgTemplate.class,directoryPath,Arrays.asList(INSERT,DELETE,UPDATE,LIST,GET_COUNT,GET_MAX_ID,FIND_BY_ID));
//        createMapper(EmailValidate.class,directoryPath,Arrays.asList(INSERT,DELETE,UPDATE,GET_COUNT,GET_MAX_ID,FIND_BY_ID));
//        createMapper(ArticleAboutInfo.class,directoryPath,Arrays.asList(INSERT,DELETE,UPDATE,FIND_BY_ID));
//        createMapper(FocusArticle.class,directoryPath,Arrays.asList(INSERT,DELETE,GET_MAX_ID,LIST));
//        createMapper(UserEffectArticle.class,directoryPath,Arrays.asList(INSERT,UPDATE,GET_MAX_ID));
//        createMapper(ImageInfo.class,directoryPath,Arrays.asList(INSERT,DELETE,GET_MAX_ID,FIND_BY_ID));
//        createMapper(Comment.class,directoryPath,Arrays.asList(INSERT,DELETE,GET_MAX_ID,FIND_BY_ID,LIST,GET_COUNT));
//        createMapper(UserEffectComment.class,directoryPath,Arrays.asList(INSERT,GET_MAX_ID));
//        createMapper(CommentAboutInfo.class,directoryPath,Arrays.asList(INSERT,UPDATE,FIND_BY_ID,DELETE));
//        createMapper(Recommend.class,directoryPath,Arrays.asList(INSERT,UPDATE,LIST,DELETE,GET_COUNT,GET_MAX_ID,FIND_BY_ID));
//        createMapper(Suggestion.class,directoryPath,Arrays.asList(INSERT,UPDATE,LIST,DELETE,GET_COUNT,GET_MAX_ID,FIND_BY_ID));
//        createMapper(SiteInfo.class,directoryPath,Arrays.asList(INSERT,UPDATE,FIND_BY_ID));
//        createMapper(SiteSetting.class,directoryPath,Arrays.asList(INSERT,UPDATE,FIND_BY_ID));
//        createMapper(Admin.class,directoryPath,Arrays.asList(INSERT,UPDATE,FIND_BY_ID));
//        createMapper(AdminLoginLog.class,directoryPath,Arrays.asList(INSERT,LIST,GET_COUNT,GET_MAX_ID));
//        createMapper(FocusUser.class,directoryPath,Arrays.asList(INSERT,LIST,GET_COUNT,GET_MAX_ID,DELETE,FIND_BY_ID));
//        createMapper(UserAboutInfo.class,directoryPath,Arrays.asList(INSERT,UPDATE,DELETE,FIND_BY_ID));
//        createMapper(UserMsg.class,directoryPath,Arrays.asList(INSERT,UPDATE,DELETE,FIND_BY_ID));
//        createMapper(Column.class,directoryPath,Arrays.asList(INSERT,UPDATE,DELETE,LIST,GET_MAX_ID));
//        createMapper(Tag.class,directoryPath,Arrays.asList(INSERT,UPDATE,LIST,GET_MAX_ID,GET_COUNT,FIND_BY_ID));
//        createMapper(BrowseRecord.class,directoryPath,Arrays.asList(INSERT,UPDATE,LIST,GET_MAX_ID,GET_COUNT,FIND_BY_ID));
        createMapper(CodeImage.class,directoryPath,Arrays.asList(INSERT,FIND_BY_ID));
    }

    private static final String INSERT = "INSERT";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE = "DELETE";
    private static final String LIST = "LIST";
    private static final String FIND_BY_ID = "FIND_BY_ID";
    private static final String GET_COUNT = "GET_COUNT";
    private static final String GET_MAX_ID = "GET_MAX_ID";
    private static final String LIST_BY_FROM_ID = "LIST_BY_FROM_ID";

    private static String testDirectoryPath = "D:" + File.separator + "workspace" + File.separator + "dg" + File.separator + "src" + File.separator + "test" + File.separator
            + "resources" + File.separator + "sql";
    private static String directoryPath = "D:" + File.separator + "workspace" + File.separator + "dg" + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "sql";

    private static void createMapper(Class clazz,String directoryPath,List<String> elementType) {
        Document document = DocumentHelper.createDocument();
        document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
        Element mapper = document.addElement("mapper");
        mapper.addAttribute("namespace", getDomainRepository(clazz));
        Element resultMap = mapper.addElement("resultMap");
        resultMap.addAttribute("id", getSqlName(clazz.getSimpleName(),"m_"))
                .addAttribute("type", clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            if (name.equals("id")) {
                resultMap.addElement("id")
                        .addAttribute("column", getSqlName(name,"f_"))
                        .addAttribute("property", name);
            }else{
                resultMap.addElement("result")
                        .addAttribute("column", getSqlName(name,"f_"))
                        .addAttribute("property", name);
            }
        }
        if(elementType.contains(INSERT)){
            addInsertElement(mapper, clazz);
        }
        if(elementType.contains(UPDATE)){
            addUpdateElement(mapper,clazz);
        }
        if(elementType.contains(DELETE)){
            addDeleteElement(mapper,clazz);
        }
        if(elementType.contains(LIST)){
            addListElement(mapper,clazz);
        }
        if(elementType.contains(FIND_BY_ID)){
            addFindByIdElement(mapper,clazz);
        }
        if(elementType.contains(GET_COUNT)){
            addGetCountElement(mapper,clazz);
        }
        if(elementType.contains(GET_MAX_ID)){
            addGetMaxIdElement(mapper,clazz);
        }
        if(elementType.contains(LIST_BY_FROM_ID)){
            addListByFromId(mapper,clazz);
        }
        try {
            String name = changeFirstCharToLower(clazz.getSimpleName());
            String filePath = directoryPath + File.separator + name + "Mapper.xml";
            XMLWriter writer = new XMLWriter(new FileWriter(filePath));
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addListElement(Element mapper,Class clazz){
        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(getSqlName(clazz.getSimpleName(),"m_"))
                .append(" order by f_id limit #{start},#{size}");
        mapper.addElement("select")
                .addAttribute("id","list")
                .addAttribute("resultMap",getSqlName(clazz.getSimpleName(),"m_"))
                .addText(sql.toString());
    }

    private static void addFindByIdElement(Element mapper,Class clazz){
        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(getSqlName(clazz.getSimpleName(),"m_")).append(" where f_id=#{id}");
        mapper.addElement("select")
                .addAttribute("id","findById")
                .addAttribute("resultMap", getSqlName(clazz.getSimpleName(),"m_"))
                .addText(sql.toString());
    }

    private static void addGetCountElement(Element mapper,Class clazz){
        StringBuilder sql = new StringBuilder("select count(f_id) FROM ");
        sql.append(getSqlName(clazz.getSimpleName(),"m_"));
        mapper.addElement("select")
                .addAttribute("id","getCount")
                .addAttribute("resultType","int")
                .addText(sql.toString());
    }

    private static void addGetMaxIdElement(Element mapper,Class clazz){
        StringBuilder sql = new StringBuilder("select IFNULL(max(f_id),10000) FROM ");
        sql.append(getSqlName(clazz.getSimpleName(),"m_"));
        mapper.addElement("select")
                .addAttribute("id","getMaxId")
                .addAttribute("resultType","int")
                .addText(sql.toString());
    }

    private static String getDomainRepository(Class clazz) {
        return clazz.getName().replace("domain", "repository") + "Repository";
    }


    private static String changeFirstCharToLower(String string) {
        char[] chars = new char[1];
        chars[0] = string.charAt(0);
        String temp = new String(chars);
        string = string.replaceFirst(temp, temp.toLowerCase());
        return string;
    }

    private static void addInsertElement(Element mapper, Class clazz) {
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(getSqlName(clazz.getSimpleName(),"m_")).append(" ( ");
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder values = new StringBuilder("(");
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            sql.append(getSqlName(name,"f_"));
            Class type = fields[i].getType();
            values.append("#{").append(name).append(",jdbcType=");
            findJdbcType(values, type);
            values.append("}");

            if (i != fields.length - 1) {
                sql.append(",");
                values.append(",");
            }
        }
        sql.append(")").append(" values ").append(values).append(")");
        mapper.addElement("insert")
                .addAttribute("id", "create")
                .addAttribute("parameterType", clazz.getName())
                .addText(sql.toString());
    }

    private static void addUpdateElement(Element mapper, Class clazz) {
        StringBuilder sql = new StringBuilder("update ");
        sql.append(getSqlName(clazz.getSimpleName(),"m_"));
        sql.append(" set ");
        Field[] fields = clazz.getDeclaredFields();
        for (int i=0;i<fields.length;i++) {
            Field field = fields[i];
            if (field.getName().equals("id")) {
                continue;
            }
            sql.append(getSqlName(field.getName(),"f_")).append("=#{").append(field.getName())
                    .append(",jdbcType=");
            findJdbcType(sql,field.getType());
            sql.append("}");
            if(i!=fields.length-1){
                sql.append(",");
            }
        }
        sql.append(" where f_id=#{id}");
        mapper.addElement("update")
                .addAttribute("id", "update")
                .addAttribute("parameterType", clazz.getName())
                .addText(sql.toString());
    }

    private static void addDeleteElement(Element mapper,Class clazz){
        StringBuilder sql = new StringBuilder("delete from ");
        sql.append(getSqlName(clazz.getSimpleName(),"m_"))
                .append(" where f_id=#{id}");
        mapper.addElement("delete")
                .addAttribute("id","delete")
                .addText(sql.toString());
    }

    private static void addListByFromId(Element mapper,Class clazz){
        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(getSqlName(clazz.getSimpleName(),"m_")).append(" where f_from_id=#{fromId}");
        mapper.addElement("select")
                .addAttribute("id","listByFromId")
                .addText(sql.toString());
    }

    private static void findJdbcType(StringBuilder builder, Class type) {
        if (type == byte.class) {
            builder.append("TINYINT");
        } else if (type == short.class) {
            builder.append("SMALLINT");
        } else if (type == int.class) {
            builder.append("INTEGER");
        } else if (type == long.class) {
            builder.append("BIGINT");
        } else if (type == boolean.class) {
            builder.append("TINYINT");
        } else if (type == float.class) {
            builder.append("FLOAT");
        } else if (type == double.class) {
            builder.append("DOUBLE");
        } else if (type == String.class) {
            builder.append("VARCHAR");
        }
    }

    private static String getSqlName(String fieldName, String prefix){
        char[] chars = fieldName.toCharArray();
        StringBuilder builder = new StringBuilder(prefix);
        for(int i=0;i<chars.length;i++){
            if(chars[i]>='A'&&chars[i]<'Z'){
                if(i!=0){
                    builder.append("_");
                }
                builder.append(String.valueOf(chars[i]).toLowerCase());
            }else{
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }
}