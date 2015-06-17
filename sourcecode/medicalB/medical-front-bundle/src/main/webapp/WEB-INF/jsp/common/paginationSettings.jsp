<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="display: none;">
<form id="paginationOptionsForm" name="paginationoptions">
	<p><label for="items_per_page">PageSize</label><input type="hidden" value="${pageSize}" name="items_per_page" id="items_per_page" class="numeric"/></p>
	<p><label for="num_display_entries">Number of pagination links shown</label><input type="hidden" value="5" name="num_display_entries" id="num_display_entries" class="numeric"/></p>
	<p><label for="num">Number of start and end points</label><input type="hidden" value="2" name="num_edge_entries" id="num_edge_entries" class="numeric"/></p>
	<p><label for="prev_text">"Previous" label</label><input type="hidden" value="上页" name="prev_text" id="prev_text"/></p>
	<p><label for="next_text">"Next" label</label><input type="hidden" value="下页" name="next_text" id="next_text"/></p>
</form>
</div>
