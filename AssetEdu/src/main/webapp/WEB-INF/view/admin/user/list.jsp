<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="kfs" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="asset"  uri="/WEB-INF/asset-tags/asset.tld"%>

<!DOCTYPE html>
<html>
<head>
<!-- =================================================== -->
<jsp:include page="../../common/meta_css.jsp" flush="false" />
<!-- =================================================== -->
<title><c:out value="${pageTitle}" default="User List" /></title>
</head>
<style>
.table tbody tr.highlight td {
  background-color: #EAF0F7;
}
</style>
<body>
<!-- =================================================== -->
<jsp:include page="../../common/header.jsp" flush="false" />
<!-- =================================================== -->
<main class="container mx-3 my-3">
  
	<h2><i class="fa-solid fa-cube my-3"></i> USER LIST</h2>
  
	<div class="container-lg p-3 border border-2 rounded-1">
		<input type="text" class="form-control w-25 d-inline align-middle" placeholder="검색어를 입력하세요" id="searchText" name="searchText" value="${param.searchText}">
		<a class="btn d-inline align-middle btn-primary btnRetrieve"><i class="fa-solid fa-search"></i> 조회</a>
		<a class="btn d-inline align-middle btn-success" href="/admin/user/insert"> <i class="fa-solid fa-user-plus"></i> 등록</a>
	</div>

	<table class="table table-sm table-hover mt-3 userTable" style="font-size:small">
	  <thead class="table-light">
	    <tr>
	      <th scope="col" class="text-center" style="width:50px">No</th>
	      <th style="width:30px"></th>
	      <th scope="col">User Id</th>
	      <th scope="col">User Name</th>
	      <th scope="col">User Tel</th>
	      <th scope="col">User Email</th>
	      <th scope="col" style="width:150px"></th>
	    </tr>
	  </thead>
	  <tbody class="table-group-divider">
	  	<c:forEach var="user" items="${list}" varStatus="status">
		    <tr class="align-middle">
		      <td scope="row" class="text-center fw-bold">${status.count }</td>
		      <td></td>
		      <td>${user.sys01UserId }</td>
		      <td>${user.sys01UserNm }</td>
		      <td>${user.sys01Tel }</td>
		      <td>${user.sys01Email }</td>
		      <td>
			      <button class="btn btn-primary btn-sm btnModify" data-user-id="${user.sys01UserId }"><span><i class="fa-regular fa-pen-to-square"></i></span> 수정</button>
			      <button class="btn btn-danger btn-sm btnDelete" data-user-id="${user.sys01UserId }" data-user-nm="${user.sys01UserNm }"><span><i class="fa-regular fa-trash-can"></i></span> 삭제</button>
		      </td>
		    </tr>
	    </c:forEach>
	  </tbody>
	</table>
	<kfs:Pagination pageAttr="${pageAttr }" id="userPagination" functionName="go"></kfs:Pagination>
	<div>
		<p>
			페이지: ${pageAttr.currentPageNumber } / ${pageAttr.totalPageCount } - 전체 갯수: ${pageAttr.totalItemCount }
		</p>
	</div>
</main>
<!-- =================================================== -->
<jsp:include page="../../common/footer.jsp" flush="false" />
<!-- -================================================== -->
<script>
$(document).ready(function () {
	console.log('ready...');
	
	//테이블 클릭시 하이라이트 표시
    $('.userTable').on('click', 'tbody tr', function(event) {
        $(this).addClass('highlight').siblings().removeClass('highlight');
    });
	
 	//조회버튼
	$('.btnRetrieve').on('click', function(){
		var searchText = $('#searchText').val();
		AssetUtil.submitGet('/admin/user/list', {searchText: searchText});
	});
  
  	
 	//수정버튼
	$('.btnModify').on('click', function(){
		var userId = $(this).data('user-id');
		AssetUtil.submitGet('/admin/user/update', {sys01UserId: userId});
	});
 	
 	//삭제버튼
	$('.btnDelete').on('click', function(){
		var userId = $(this).data('user-id');
		var userNm = $(this).data('user-nm');
		if(confirm("사용자 " + userNm + "을(를) 삭제하시겠습니까?")){
			AssetUtil.submitGet('/admin/user/delete', {userId: userId});	
		}
		
	});
	//검색창에서 enter이면 조회
	$("#searchText").on("keyup",function(key){
        if(key.keyCode==13) { //enter
        	$('.btnRetrieve').trigger('click');
        }else if(key.keyCode == 46){ //DEL
        	$(this).val('');
        }
	});
});
function go(no){
	alert(no + "페이지입니다.");
	var searchText = $('#searchText').val();
	AssetUtil.submitGet('/admin/user/list', {currentPageNumber : no, searchText: searchText});
}
</script>
</body>
</html>