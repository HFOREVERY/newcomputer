<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="list-group">
	<a href="TeaQueryGroupAction" class="list-group-item" id="sideQueryGroup">查询分组</a>
	<a href="teaQueryProgress.jsp" class="list-group-item" id="sideQueryProgress">查询进度报告</a>
</div>


<script type="text/javascript">
	function updateClassNameOfLink(id) {
		$('#sideQueryGroup').className = "list-group-item";
		$('#sideQueryGroup').className = "list-group-item";
		document.getElementById(id).className = "list-group-item active";
	}
</script>