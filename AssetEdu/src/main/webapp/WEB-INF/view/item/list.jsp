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
<jsp:include page="../common/meta_css.jsp" flush="false" />
<!-- =================================================== -->
<title><c:out value="${pageTitle}" default="Item List" /></title>
</head>
<style>
.table tbody tr.highlight td {
  background-color: #EAF0F7;
}
</style>
<body>
<!-- =================================================== -->
<jsp:include page="../common/header.jsp" flush="false" />
<!-- =================================================== -->
<main class="container mx-3 my-3">
  
	<h2><i class="fa-solid fa-cube my-3"></i>주식 종목정보 관리</h2>
  
	<div class="container-lg p-3 border border-2 rounded-1">
		<input type="text" class="form-control w-50 d-inline align-middle" placeholder="검색어(종목코드/종목명/단축코드)를 입력하세요" id="searchText" name="searchText" value="${param.searchText}">
		<a class="btn d-inline align-middle btn-primary btnRetrieve"><i class="fa-solid fa-search"></i> 조회</a>
        <a class="btn d-inline align-middle btn-secondary btnInit"><i class="fa-solid fa-backspace"></i> 초기화</a>
		<a class="btn d-inline align-middle btn-success" href="/item/insert"> <i class="fa-solid fa-plus"></i> 등록</a>
	</div>

	<table class="table table-sm table-hover mt-3 item01Table" style="font-size:small">
	  <thead class="table-light">
	    <tr>
	      <th scope="col" class="text-center" style="width:50px">No</th>
	      <th style="width:30px"></th>
	      <th scope="col">종목코드</th>
	      <th scope="col">종목한글명</th>
	      <th scope="col">종목영문명</th>
	      <th scope="col">단축코드</th>
	      <th scope="col">증권종류</th>
	      <th scope="col">상장구분</th>
	      <th scope="col">시장구분</th>
	      <th scope="col">액면가</th>
	    </tr>
	  </thead>
	  <tbody class="table-group-divider">
	  	<c:forEach var="itm01" items="${list}" varStatus="status">
		    <tr class="align-middle">
		      <td scope="row" class="text-center fw-bold">${status.count }</td>
		      <td></td>
		      <td>${itm01.itm01ItemCd }</td>
		      <td>${itm01.itm01ItemNm }</td>
		      <td>${itm01.itm01ItemEnm }</td>
		      <td>${itm01.itm01ShortCd }</td>
		      <td>${itm01.itm01StkType }</td>
		      <td>${itm01.itm01ListType }</td>
		      <td>${itm01.itm01MarketType }</td>
		      <td><fmt:formatNumber value="${itm01.itm01Par}" pattern="#,###"/></td>
		      <td>
			      <button class="btn btn-primary btn-sm btnModify" data-user-id="${itm01.itm01ItemCd }"><span><i class="fa-regular fa-pen-to-square"></i></span> 수정</button>
			      <button class="btn btn-danger btn-sm btnDelete" data-user-id="${itm01.itm01ItemCd }" data-user-nm="${itm01.itm01ItemNm }"><span><i class="fa-regular fa-trash-can"></i></span> 삭제</button>
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
<jsp:include page="../common/footer.jsp" flush="false" />
<!-- -================================================== -->
<script>
$(document).ready(function () {
	console.log('ready...');
	
	//테이블 클릭시 하이라이트 표시
    $('.item01Table').on('click', 'tbody tr', function(event) {
        $(this).addClass('highlight').siblings().removeClass('highlight');
    });
	
 	//조회버튼
	$('.btnRetrieve').on('click', function(){
		var searchText = $('#searchText').val();
		AssetUtil.submitGet('/item/list', {searchText: searchText});
	});
 	
 	//초기화버튼
    $('.btnInit').on('click', function(){
        AssetUtil.submitGet('/item/list', {searchText: null});
    });
  	
 	//수정버튼
	$('.btnModify').on('click', function(){
		var userId = $(this).data('user-id');
		AssetUtil.submitGet('/item/update', {sys01UserId: userId});
	});
 	
 	//삭제버튼
	$('.btnDelete').on('click', function(){
		var userId = $(this).data('user-id');
		var userNm = $(this).data('user-nm');
		if(confirm("종목 " + userNm + "을(를) 삭제하시겠습니까?")){
			AssetUtil.submitGet('/item/delete', {sys01UserId: userId});	
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
	AssetUtil.submitGet('/item/list', {currentPageNumber : no, searchText: searchText});
}
</script>
</body>
</html>