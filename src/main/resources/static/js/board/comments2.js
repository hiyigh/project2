document.ready(function(){
    loadComment();
});

function loadComment() {
    $.ajax({
        type:'get',
        url:'item/comment/list',
        success:function(commentList){

            if(loginName != "") {
                document.querySelector('#commentForm .input_group textarea').innerHTML = "";
                document.querySelector('#commentForm .input_group textarea').disabled = false;
                document.querySelector('#commentForm .input_group button').disabled = false;
            }
            commentList.forEach(function(comment){
                if (comment.depth == 0) {
                    $('#commentList').append(addComment(comment));
                } else {
                    $('.c' + comment.parentId).append(addComment(comment));
                }
            });

        },
        error:function(error){
        }
    });
}
function deleteComment(commentId,event) {
    let ck = confirm("delete comment?");
    if (ck == false) return;

    let row = $(event.target).parents('.commentRow');
    $.ajax({
        type:'delete',
        url:'item/api/comment/delete/' + commentId,
        success:function(response){
            let result = `<div>삭제</div>`;
            row.append(result);
        },
        error:function(error){
            alert("삭제 실패");
            console.log(error);
        }
    });
}
function updateComment(event, commentId){
    let row = $(event.target).parents('.commentRow');
    let comment =  $('#updateContent').val();
    $.ajax({
        type:'put',
        url:'/api/comment/update'
        data: {
            commentId:commentId,
            comment: comment,
        },
        success:function(comment){
            $(row).children('#commentContent').innerHTML = comment.comment;
            $('#updateForm').remove();
        },
        error:function(error){
            console.log("error", error);
            alert("update fail");
        }
    });

}
function cancelUpdate() {
    $('#updateForm').remove();
}
let prev;
function openUpdateCommentForm(commentId, event) {
    let row = $(event.target).parents('.commentRow');
    let content = $(row).children('.commentContent').val();
    let cMenu = $(comment).children('.commentMenu');

    let result = `
        <form>
            <div id="updateForm">
                <div class="update">
                    <input type="text" id="updateContent" name="updateContent" value="${content}" />
                </div>
                <div>
                    <span onclick="updateComment(event, commentId)">저장</span>
                    <span onclick="cancelUpdate()">취소</span>
                </div>
            </div>
        </form>
    `;

    $(row).append(result);
}
function addComment(comment) {
	let identify = comment.id + "_" + comment.depth;
	let margin = "margin-left:" + (comment.depth * 20) +"px;";
	let replyIcon = `<i class="fas fa-reply"></i>`;
	if(comment.parentId == 0) {
	    replyIcon = "";
	}
	let replySpan = `<span onclick="openReplyForm('${identify}',event)">답글</span>`
	if(loginName == "" || comment.depth == 2) {
	    replySpan = "";
	}
	let commentMenu = `
	<span onclick="deleteComment('${comment.id}',event)">삭제</span>
	<span onclick="openUpdateCommentForm('${comment.id}',event)">수정</span>
	`;
	if(loginName != comment.writer) {
	    commentMenu = "";
	}
	let result = `
	<div class = "commentWrap c${comment.id}">
	    <div class="row commentRow" style="${margin}">
	        <div class="mb-2">
	            <i class="fas fa-user"></i><span class="commentWriter" onclick="pop(event, '')">${comment.writer}</span>
	            <span>${comment.createdAt}</span>
	        </div>
	        <div class="mb-2 commentContent">${comment.comment}</div>
	        <div class="mb-2 commentMenu">
	            ${replySpan}
	            <div style="float:right;">
	                ${commentMenu}
	            </div>
	        </div>
            ${replyIcon}
	    </div>
	</div>
	`;

	if(comment.viewStatus == 0) {
	    result =`
	    <div class="commentWrap c${comment.id}">
	        <div class="row commentRow">
	            <div>삭제</div>
	        </div>
	    </div>
	    `;
	}
	return result;
}