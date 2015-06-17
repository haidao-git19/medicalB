<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="/WEB-INF/jsp/web/common.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/web/head.jsp"></jsp:include>
<title>找医院-医保宝</title>
<meta http-equiv='Cache-Control' content='no-siteapp' />
<script src="${ctx }/resources/web/js/global.js" type="text/javascript"></script>
<link href='${ctx }/resources/web/css/mywl.css' rel='stylesheet' type='text/css' />
<link href='${ctx }/resources/web/css/global-v5.css' rel='stylesheet' type='text/css' />
<link href='${ctx }/resources/web/css/popup.css' rel='stylesheet' type='text/css' />
<link href='${ctx }/resources/web/css/imgareaselect-default.css' rel='stylesheet' type='text/css' />
    </head>
    <body>

<div class="blk20 clear"></div>
<div class="main clearfix">
  <div class="mywl-left">
    <div class="mywl-left-cnt">
      <div class="mywl-left-head"> <a href="javascript:;" id="btn_page_left_menu_user" class="qz qz-on">我的中心</a>  </div>
      <div id="box_page_left_menu_user" class="blu" >
        <h3 ><i></i>医疗服务</h3>
        <ul >
          <li><a onClick="homeleft_set_current_tab_in(0)" class="current" href="#">我的服务</a></li>
          <li><a onClick="homeleft_set_current_tab_in(0)"  href="#">我的医生</a></li>
        </ul>
        <h3 ><i></i>我的账户</h3>
        <ul >
          <li><a onClick="homeleft_set_current_tab_in(0)"  href="/user/bole/concerned/">我的病历资料</a></li>
          <li><a onClick="homeleft_set_current_tab_in(0)"  href="/user/bole/concerned/?type=2">我的财务</a></li>
        </ul>
      </div>
      
    </div>
    <div class="blk15"></div>
    <div class="mywl-left-cnt">
      <div class="mywl-grnline"></div>
      <div class="grn" id="box_page_left_menu_common">
        <h3 id="left_btn_h3_1" class="on"><i></i>私信管理</h3>
        <ul style="display: none;">
          <li> <a  href="/user/message/index/">我的私信</a> </li>
        </ul>
        <h3 id="left_btn_h3_4" class="on"><i></i>账号管理</h3>
        <ul style="display: none;">
          <li><a  href="/user/account/passwordModify/">更改密码</a></li>
          <li><a  href="/user/account/email_modify/">更改邮箱</a></li>
        </ul>
      </div>
    </div>
  </div>
 <script type="text/javascript">
                $(function(){
                    $('.mywl-left h3').click(function(){
                        $(this).next('ul').toggle();
                        $(this).toggleClass('on');
                    });

                    $('#btn_page_left_menu_user').click(function(){
                        $('#btn_page_left_menu_hr').removeClass('zp-on');
                        $(this).addClass('qz-on');
                        $('#box_page_left_menu_hr').hide();
                        $('#box_page_left_menu_user').show();
                    });

                    $('#btn_page_left_menu_hr').click(function(){
                        $('#btn_page_left_menu_user').removeClass('qz-on');
                        $(this).addClass('zp-on');
                        $('#box_page_left_menu_user').hide();
                        $('#box_page_left_menu_hr').show();
                    });

                });
            </script>
  <div class="mywl-right">
    <div class="mywlR-top">
      <div class="mywlR-top-title"> 
         	我的基本信息
          </div>
    </div>

    
    <div class="mywl-grnline"></div>
    
    <div class="blk20"></div>
    
    <!-- 基本信息 -->
    <div id="id_basic">
      <h4>基本信息（<b>20%</b>）必填</h4>
      <div class="blk30"></div>
      <div class="mywl-right-list" id="informationShowDiv" style="display:none;">
        <div>
          <ul class="rowList cf">
            <li><span>姓　　名：</span></li>
            <li><span>性　　别：</span> </li>
            <li><span>出生年月：</span> </li>
            <li><span>手机号码：</span></li>
            <li><span>登陆密码：</span></li>
            <li><span>现居住地：</span></li>
          </ul>
          <div class="touxiang"><a href="javascript:;" onClick="javascript: return false;"><img src="http://static.wealinkcdn.com/v4/img/tx150.jpg" alt="头像" width="148px" height="148px;"></a><a  href="javascript:void(0);" onClick="javascript: showavatar(this); return false;" class="ghtx"> 上传头像 </a></div>
          <div class="blk10"></div>
          <div class="action">
            <button type="button" class="positive" id="id_add_basic_btn" onClick="user_basci_edit()">修改</button>
          </div>
        </div>
      </div>
      <div class="mywl-right-form" id="informationModifyDiv" >
        <div id="informationMsg"></div>
        <ul class="cf">
          <li>
            <label for=""><span class="ness">*</span>姓　　名：</label>
            <div class="autoCom">
              <input type="text" placeholder="请输入真实中文姓名" name="user_name" id="user_name" value="">
            </div>
          </li>
          <li> <span class="label"><span class="ness">*</span>性　　别：</span>
            <label class="label-radio">
              <input class="ipt-radio" type="radio" name="sex" value="M"  style="border: 0px; ">
              男 </label>
            <label class="label-radio">
              <input class="ipt-radio" type="radio" name="sex" value="F"  style="border: 0px; ">
              女 </label>
          </li>
          <li class="selectlist-li"> <span class="label"><span class="ness">*</span>出生年份：</span>
            <dl class="selectList yeardl">
              <dt><span id="birth_yearSpan">年</span>
                <input type="hidden" name="birth_year" id="birth_year" value=""/>
                <i></i></dt>
              <dd class="optionList cf">
                <ul id="birth_yearUl" class="ulLimit cf">
                  <li><a onClick="javascript: chooseLi('2015','birth_year'); return false;">2015</a></li>
                  <li><a onClick="javascript: chooseLi('2014','birth_year'); return false;">2014</a></li>
                  <li><a onClick="javascript: chooseLi('2013','birth_year'); return false;">2013</a></li>
                  <li><a onClick="javascript: chooseLi('2012','birth_year'); return false;">2012</a></li>
                  <li><a onClick="javascript: chooseLi('2011','birth_year'); return false;">2011</a></li>
                  <li><a onClick="javascript: chooseLi('2010','birth_year'); return false;">2010</a></li>
                  <li><a onClick="javascript: chooseLi('2009','birth_year'); return false;">2009</a></li>
                  <li><a onClick="javascript: chooseLi('2008','birth_year'); return false;">2008</a></li>
                  <li><a onClick="javascript: chooseLi('2007','birth_year'); return false;">2007</a></li>
                  <li><a onClick="javascript: chooseLi('2006','birth_year'); return false;">2006</a></li>
                  <li><a onClick="javascript: chooseLi('2005','birth_year'); return false;">2005</a></li>
                  <li><a onClick="javascript: chooseLi('2004','birth_year'); return false;">2004</a></li>
                  <li><a onClick="javascript: chooseLi('2003','birth_year'); return false;">2003</a></li>
                  <li><a onClick="javascript: chooseLi('2002','birth_year'); return false;">2002</a></li>
                  <li><a onClick="javascript: chooseLi('2001','birth_year'); return false;">2001</a></li>
                  <li><a onClick="javascript: chooseLi('2000','birth_year'); return false;">2000</a></li>
                  <li><a onClick="javascript: chooseLi('1999','birth_year'); return false;">1999</a></li>
                  <li><a onClick="javascript: chooseLi('1998','birth_year'); return false;">1998</a></li>
                  <li><a onClick="javascript: chooseLi('1997','birth_year'); return false;">1997</a></li>
                  <li><a onClick="javascript: chooseLi('1996','birth_year'); return false;">1996</a></li>
                  <li><a onClick="javascript: chooseLi('1995','birth_year'); return false;">1995</a></li>
                  <li><a onClick="javascript: chooseLi('1994','birth_year'); return false;">1994</a></li>
                  <li><a onClick="javascript: chooseLi('1993','birth_year'); return false;">1993</a></li>
                  <li><a onClick="javascript: chooseLi('1992','birth_year'); return false;">1992</a></li>
                  <li><a onClick="javascript: chooseLi('1991','birth_year'); return false;">1991</a></li>
                  <li><a onClick="javascript: chooseLi('1990','birth_year'); return false;">1990</a></li>
                  <li><a onClick="javascript: chooseLi('1989','birth_year'); return false;">1989</a></li>
                  <li><a onClick="javascript: chooseLi('1988','birth_year'); return false;">1988</a></li>
                  <li><a onClick="javascript: chooseLi('1987','birth_year'); return false;">1987</a></li>
                  <li><a onClick="javascript: chooseLi('1986','birth_year'); return false;">1986</a></li>
                  <li><a onClick="javascript: chooseLi('1985','birth_year'); return false;">1985</a></li>
                  <li><a onClick="javascript: chooseLi('1984','birth_year'); return false;">1984</a></li>
                  <li><a onClick="javascript: chooseLi('1983','birth_year'); return false;">1983</a></li>
                  <li><a onClick="javascript: chooseLi('1982','birth_year'); return false;">1982</a></li>
                  <li><a onClick="javascript: chooseLi('1981','birth_year'); return false;">1981</a></li>
                  <li><a onClick="javascript: chooseLi('1980','birth_year'); return false;">1980</a></li>
                  <li><a onClick="javascript: chooseLi('1979','birth_year'); return false;">1979</a></li>
                  <li><a onClick="javascript: chooseLi('1978','birth_year'); return false;">1978</a></li>
                  <li><a onClick="javascript: chooseLi('1977','birth_year'); return false;">1977</a></li>
                  <li><a onClick="javascript: chooseLi('1976','birth_year'); return false;">1976</a></li>
                  <li><a onClick="javascript: chooseLi('1975','birth_year'); return false;">1975</a></li>
                  <li><a onClick="javascript: chooseLi('1974','birth_year'); return false;">1974</a></li>
                  <li><a onClick="javascript: chooseLi('1973','birth_year'); return false;">1973</a></li>
                  <li><a onClick="javascript: chooseLi('1972','birth_year'); return false;">1972</a></li>
                  <li><a onClick="javascript: chooseLi('1971','birth_year'); return false;">1971</a></li>
                  <li><a onClick="javascript: chooseLi('1970','birth_year'); return false;">1970</a></li>
                  <li><a onClick="javascript: chooseLi('1969','birth_year'); return false;">1969</a></li>
                  <li><a onClick="javascript: chooseLi('1968','birth_year'); return false;">1968</a></li>
                  <li><a onClick="javascript: chooseLi('1967','birth_year'); return false;">1967</a></li>
                  <li><a onClick="javascript: chooseLi('1966','birth_year'); return false;">1966</a></li>
                  <li><a onClick="javascript: chooseLi('1965','birth_year'); return false;">1965</a></li>
                  <li><a onClick="javascript: chooseLi('1964','birth_year'); return false;">1964</a></li>
                  <li><a onClick="javascript: chooseLi('1963','birth_year'); return false;">1963</a></li>
                  <li><a onClick="javascript: chooseLi('1962','birth_year'); return false;">1962</a></li>
                  <li><a onClick="javascript: chooseLi('1961','birth_year'); return false;">1961</a></li>
                  <li><a onClick="javascript: chooseLi('1960','birth_year'); return false;">1960</a></li>
                  <li><a onClick="javascript: chooseLi('1959','birth_year'); return false;">1959</a></li>
                  <li><a onClick="javascript: chooseLi('1958','birth_year'); return false;">1958</a></li>
                  <li><a onClick="javascript: chooseLi('1957','birth_year'); return false;">1957</a></li>
                  <li><a onClick="javascript: chooseLi('1956','birth_year'); return false;">1956</a></li>
                  <li><a onClick="javascript: chooseLi('1955','birth_year'); return false;">1955</a></li>
                  <li><a onClick="javascript: chooseLi('1954','birth_year'); return false;">1954</a></li>
                  <li><a onClick="javascript: chooseLi('1953','birth_year'); return false;">1953</a></li>
                  <li><a onClick="javascript: chooseLi('1952','birth_year'); return false;">1952</a></li>
                  <li><a onClick="javascript: chooseLi('1951','birth_year'); return false;">1951</a></li>
                  <li><a onClick="javascript: chooseLi('1950','birth_year'); return false;">1950</a></li>
                </ul>
              </dd>
            </dl>
          
          </li>
          <li>
            <label for=""><span class="ness">*</span>手机号码：</label>
            <input type="text" name="mobile" id="mobile" value=""  placeholder="登陆唯一凭证"/>
          </li>
            <li>
            <label for=""><span class="ness">*</span>登陆密码：</label>
            <input type="text" name="mobile" id="mobile" value=""  />
          </li>
 
          <li> <span class="label"><span class="ness">*</span>患者卡号：</span>
            <input type="text" name="mobile" id="mobile" value=""  />
          </li>
        </ul>
        <div class="blk10"></div>
      </div>
      <div class="blk30"></div>
    </div>
    <!-- 基本信息 end--> 

    

    
    <!-- 大保存 -->
    <div class="page-shadow"></div>
    <div class="saveVita">
      <button type="button" class="tjbc save_profile_all" onClick="save_profile_check()">提交保存</button>
    <div class="blk30"></div>
    <!-- 大保存 end --> 
    

  </div>
</div>

<jsp:include page="/WEB-INF/jsp/web/foot.jsp"></jsp:include>
</body>
</html>