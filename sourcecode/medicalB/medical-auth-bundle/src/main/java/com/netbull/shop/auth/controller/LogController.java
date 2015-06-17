package com.netbull.shop.auth.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.dao.HDao;
import com.netbull.shop.util.DataTablesQuery;
import com.netbull.shop.util.HttpUtils;
import com.netbull.shop.auth.entity.OptLogInfo;

@Controller("logController")
public class LogController {
	
	@Resource
	private HDao authDao;

	private DataTablesQuery logQuery = new DataTablesQuery() {
		
		protected String buildQuery(Map<String, String> params, List<Object> paramValues) {
			String hql = "from OptLogInfo oli where 1=1";
			String text = params.get("text");
			if (StringUtils.hasText(text)) {
				hql += " and (oli.loginName like ? or oli.optName like ?"
						+ " or oli.loginName in(select loginName from UserInfo where trueName like ?))";
				paramValues.add("%" + text + "%");
				paramValues.add("%" + text + "%");
				paramValues.add("%" + text + "%");
			}
			String startDate = params.get("startDate");
			String endDate = params.get("endDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.hasText(startDate)) {
				try {
					Date start = sdf.parse(startDate);
					hql += " and oli.optTime >= ?";
					paramValues.add(start);
				} catch (ParseException e) {
				}
			}
			if(StringUtils.hasText(endDate)) {
				try{
					Date end = sdf.parse(endDate);
					Calendar cal = Calendar.getInstance();
					cal.setTime(end);
					cal.add(Calendar.DAY_OF_MONTH, 1);
					end = cal.getTime();
					hql += " and oli.optTime < ?";
					paramValues.add(end);
				}catch(ParseException e) {
				}
			}
			hql += " order by oli.optTime desc";
			return hql;
		}

		protected Object toRowDatas(List<Object> datas) {
			List<Object> rows = new ArrayList<Object>();
			for (Object data : datas) {
				OptLogInfo log = (OptLogInfo) data;
				List<Object> row = new ArrayList<Object>();
				row.add(log.getOptName());
				row.add(log.getLoginName());
				row.add(log.getOptTime());
				rows.add(row);
			}
			return rows;
		}

	};

	@RequestMapping("/log")
	public String log() {
		return "auth/log/log";
	}

	@RequestMapping("/log/pageQuery")
	@ResponseBody
	public Object pageQuery(HttpServletRequest request) {
		return logQuery.queryPage(HttpUtils.getParams(request), authDao);
	}

}
