<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="list-group">
	<a href="index.jsp" class="list-group-item" id="sideIndex">分组</a>
	<a href="querygroup.jsp" class="list-group-item" id="sideQueryGroup">查询分组</a>
	<a href="submitprogress.jsp" class="list-group-item" id="sideSubmitProgress">进度报告</a>
	<a href="queryprogress.jsp" class="list-group-item" id="sideQueryProgress">查询进度报告</a>
</div>


<script type="text/javascript">
	function updateClassNameOfLink(id) {
		$('#sideIndex').className = "list-group-item";
		$('#sideQueryGroup').className = "list-group-item";
		$('#sideSubmitProgress').className = "list-group-item";
		$('#sideQueryProgress').className = "list-group-item";
		document.getElementById(id).className = "list-group-item active";
	}
</script>