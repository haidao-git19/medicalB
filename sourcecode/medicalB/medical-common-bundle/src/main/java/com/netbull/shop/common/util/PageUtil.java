package com.netbull.shop.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.netbull.shop.common.task.Const;

/**
 * Page对象的使用: Page page = new Page(request,pageSize); List list =
 * xxx.getList();<br/>
 * page.setTotleRow(totleRows);<br/>
 * 非线程安全类
 * 
 * @author Administrator
 */
public class PageUtil {

    /** 第一页 页号 */
    private static final int FIRST_PAGE_NUM = 1;

    /** 页面大小 */
    private int pageSize = 10;

    /** 当前页号, 从1开始 */
    private int currentPage = FIRST_PAGE_NUM;

    /** 上一页 */
    private int previousPage = FIRST_PAGE_NUM;

    /** 下一页 */
    private int nextPage = FIRST_PAGE_NUM;

    /** 总页数 */
    private int totlePage = FIRST_PAGE_NUM;

    /** 结果集 */
    @SuppressWarnings("unchecked")
    private List list;

    /** pageHTML */
    private String pageHTML;

    /** url */
    private String url;

    /** pageParam */
    private String pageParam;

    /** 查询条件 */
    @SuppressWarnings("unchecked")
    private Map param = new HashMap();

    /* ######################################### */
    /* 以下是新修改的PAGE构造 2007-12-1 */

    public PageUtil() {

    }

    /**
     * @param page - 页码
     */
    public PageUtil(String page) {
        try {
            currentPage = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            currentPage = FIRST_PAGE_NUM;
        }
    }

    /**
     * @param page - 页码
     * @param pageParam - 翻页参数
     */
    public PageUtil(String page, String pageParam) {
        try {
            currentPage = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            currentPage = FIRST_PAGE_NUM;
        }
        this.setPageParam(pageParam);// 翻页参数
    }

    /**
     * @param url - 页面地址
     * @param page - 页码
     * @param pageParam - 翻页参数
     */
    public PageUtil(String page, String url, String pageParam) {
        try {
            currentPage = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            currentPage = FIRST_PAGE_NUM;
        }
        this.setUrl(url);
        this.setPageParam(pageParam);// 翻页参数
    }

    /**
     * @param url - 页面地址
     * @param page - 页码
     * @param pageParam - 翻页参数
     */
    public PageUtil(int page, String url, String pageParam) {

        currentPage = (page < 1) ? 1 : page;
        this.setUrl(url);
        this.setPageParam(pageParam);// 翻页参数
    }

    /**
     * @param url - 页面地址
     * @param page - 页码
     * @param param - 翻页查询条件
     */
    @SuppressWarnings("unchecked")
    public PageUtil(String page, String url, Map param) {
        try {
            currentPage = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            currentPage = FIRST_PAGE_NUM;
        }
        this.setUrl(url);
        this.setPageParam(pageParam);// 翻页参数
        this.setParam(param);
    }

    /**
     * @param url - 页面地址
     * @param page - 页码
     * @param pageParam - 翻页参数
     * @param param - 翻页查询条件
     */
    @SuppressWarnings("unchecked")
    public PageUtil(String page, String url, String pageParam, Map param) {
        try {
            currentPage = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            currentPage = FIRST_PAGE_NUM;
        }
        this.setUrl(url);
        this.setPageParam(pageParam);// 翻页参数
        this.setParam(param);
    }

    /* ######################################### */

    /**
     * 根据页码计算记录位置
     */
    public int getStart() {
        return (this.getCurrentPage() - 1) * pageSize;
    }

    /**
     * @return Returns the currentPage.
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @return Returns the nextPage.
     */
    public int getNextPage() {
        return this.nextPage;
    }

    /**
     * @return Returns the previousPage.
     */
    public int getPreviousPage() {
        return this.previousPage;
    }

    /**
     * @return Returns the totlePage.
     */
    public int getTotlePage() {
        return totlePage;
    }

    public int getPageSize() {
        return pageSize;
    }

    /** 设置总记录数,并计算出总页数 */
    public void setTotleRow(int totleRow) {
        totlePage = (totleRow % pageSize == 0 ? totleRow / pageSize : totleRow / pageSize + 1);

        /** 当前页 */
        if (currentPage > totlePage && totlePage != 0) {
            currentPage = totlePage;
        } else if (currentPage < 1) {
            currentPage = FIRST_PAGE_NUM;
        }

        /** 上一页 */
        if (currentPage > 1) {
            previousPage = currentPage - 1;
        } else {
            previousPage = FIRST_PAGE_NUM;
        }

        /** 下一页 */
        if (currentPage < totlePage) {
            nextPage = currentPage + 1;
        } else if (totlePage != 0) {
            nextPage = totlePage;
        } else {
            nextPage = FIRST_PAGE_NUM;
        }
    }

    @SuppressWarnings("unchecked")
    public final List getList() {
        return list;
    }

    @SuppressWarnings("unchecked")
    public final void setList(List list) {
        this.list = list;
    }

    public final String getAjaxPageHTML() {
        if (null == list || list.isEmpty()) {
            return "";
        }

        StringBuilder str = new StringBuilder();
        str.append("<div class=\"PagingRight\"><p class=\"pagenum clearfix\">");
        str.append("<a href=\"javascript:void(0);\" onclick=\"");
        str.append(1 == this.getCurrentPage() ? "javascript:void(0);"
                : "goToPage('1');return false;");
        str.append("\">首页</a>");
        str.append("<a href=\"javascript:void(0);\" onclick=\"");
        str.append(this.getPreviousPage() == this.getCurrentPage() ? "javascript:void(0);"
                : "goToPage('" + getPreviousPage() + "');return false;");
        str.append("\">上一页</a>");
        str.append("<a href=\"javascript:void(0);\" onclick=\"");
        str.append(this.getNextPage() == this.getCurrentPage() ? "javascript:void(0);"
                : "goToPage('" + getNextPage() + "');return false;");
        str.append("\">下一页</a>");
        str.append("<a href=\"javascript:void(0);\" onclick=\"");
        str.append(this.getTotlePage() == this.getCurrentPage() ? "javascript:void(0);"
                : "goToPage('" + getTotlePage() + "');return false;");
        str.append("\">尾页</a>");
        str.append("<em>共");
        str.append(getTotlePage());
        str.append("页，当前第");
        str.append(getCurrentPage());
        str.append("页 <input name=\"pageNum\" alt='"
                + this.getTotlePage()
                + "' id='pageNum' style=\"width:22px;\" class=\"inputText2\" onfocus=\"this.className='inputClick2'\" maxlength='8' /> <input type=\"button\" name=\"button\" class=\"tzGo\" onMouseOver=\"this.className='tzGo2'\" onClick='return goToPageByNum();' onMouseOut=\"this.className='tzGo'\" value=\"GO\"></em>");
        str.append("</p></div>");

        return str.toString();
    }

    public final String getPageHTML() {
        pageHTML = (new StringBuilder())
                .append("<div class=\"PagingRight\"><p class=\"pagenum clearfix\"><a href=\""
                        + getUrl() + "?page=1" + getPageParam() + "\" >首页</a><a href=\"" + getUrl()
                        + "?page=" + getPreviousPage() + getPageParam() + "\" >上一页</a><a href=\""
                        + getUrl() + "?page=" + getNextPage() + getPageParam()
                        + "\">下一页</a><a href=\"" + getUrl() + "?page=" + getTotlePage()
                        + getPageParam() + "\">尾页</a><em>共")
                .append(getTotlePage())
                .append("页，当前第")
                .append(getCurrentPage())
                .append("页 <input name=\"page\"  id='page' style=\"width:22px;\" class=\"inputText2\" onfocus=\"this.className='inputClick2'\" /><input name='button' type='button' class=\"tzGo\" onMouseOver=\"this.className='tzGo2'\"  onClick=\"JavaScript:GO(\'"
                        + getUrl()
                        + "?page=\'+document.getElementById('page').value+\'"
                        + getPageParam()
                        + "\')\" value='GO' onMouseOut=\"this.className='tzGo'\" ></em></p></div>")
                .append("<script language='javascript'>document.getElementById('page').focus();	function GO(n){window.location.href=n;document.getElementById('page').focus();}</script>")
                .toString();

        return pageHTML;
    }

    public final String getPageParam() {
        return pageParam;
    }

    public final void setPageParam(String pageParam) {
        this.pageParam = pageParam;
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url = url;
    }

    public final void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    /** 返回无参数的limit map */
    @SuppressWarnings("unchecked")
    public final Map getParam() {
        this.param.put("start", this.getStart());
        this.param.put("size", this.getPageSize());
        this.param.put("end", this.getStart() + this.getPageSize());
        return this.param;
    }

    /**
     * 自动为单个地址加上所属菜单的前缀没有 <code>param=""</code>返回根目录去掉.do
     * <code>param=null</code>返回根目录<br/>
     * 
     * @param param - "update&id=1" 是指调用update这个方法,并且id为1
     */
    public static String getOneHref(HttpServletRequest request, HttpServletResponse response,
            String param) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        if (param == null) {
            return (String) session.getAttribute(Const.ROOT_MENU_URL);
        }
        String root_menu_url = (String) session.getAttribute(Const.ROOT_MENU_URL);

        root_menu_url = RequestUtil.subActionSuffix(root_menu_url) + Const.SIGN
                + StringUtil.getString(param);

        return response.encodeRedirectURL(root_menu_url);
    }
    
    /**
     * @param param - 可设置SQL的查询条件(包括limit后的参数)
     */
    @SuppressWarnings("unchecked")
    public final void setParam(Map param) {
        this.param = param;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

}
