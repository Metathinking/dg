<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/7/16.0016
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer>
    <div class="main-footer">

        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="widget">

                        <div class="content recent-post">
                            <div class="recent-single-post">
                                <a href="${pageContext.request.contextPath}/suggestion/edit.html" class="post-title">问题反馈</a>
                            </div>
                        </div>
                    </div>
                    <div class="widget">
                        <div class="content recent-post">
                            <div class="recent-single-post">
                                <a href="#" class="post-title">关于"我来说"</a>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-md-4">
                    <div class="widget">
                        <h4 class="title">标签云</h4>
                        <div class="content tag-cloud">
                            <a href="#">Ghost</a>
                            <a href="#">PHP</a>
                            <a href="#">JAVA</a>
                            <a href="#">BOOTSTRAP</a>
                            <a href="#">GO</a>
                            <a href="#">Ghost</a>
                            <a href="#">PHP</a>
                            <a href="#">JAVA</a>
                            <a href="#">BOOTSTRAP</a>
                            <a href="#">GO</a>
                            <a href="#">Ghost</a>
                            <a href="#">PHP</a>
                            <a href="#">JAVA</a>
                            <a href="#">BOOTSTRAP</a>
                            <a href="#">GO</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="widget">
                        <h4 class="title">合作伙伴</h4>
                        <div class="content tag-cloud friend-links">
                            <a href="#">Bootstrap 中文网</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer_bottom">
        <div class="footer_bottom_content">
           ${siteInfo.footer}
        </div>
    </div>
</footer>