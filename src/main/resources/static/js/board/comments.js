function addComment(comment) {
    let identify = comment.if + "_" + comment.depth;
    let replySpan = `<span onclick="openReplyForm('${identify}', event)">답글</span>`;
    var margin = "margin-left:"+(comment.depth * 20)+"px;";


}
function deleteComment(commentId, event) {
}