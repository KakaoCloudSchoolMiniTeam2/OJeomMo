(function () {
    let deleteBtn = document.querySelector("[name = boardDeleteBtn]");

    let boardId = document.querySelector("[name=boardId]").value;

    if (deleteBtn != null) {
        deleteBtn.onclick = deleteAlert;
    }

// delete 버튼 눌렀을 때 alert 표시
    function deleteAlert(event) {
        event.preventDefault();
        // Alert 띄우기
        const confirmed = confirm('정말로 삭제하시겠습니까?');

        // 사용자가 "확인"을 클릭한 경우에만 form 제출
        if (confirmed) {
            document.getElementById('myForm').submit();
        }
    }

    const updateBtn = document.querySelector("[name = boardUpdateBtn]");
    if (updateBtn != null) {
        updateBtn.onclick = function (boardId) {
            return function (event) {
                event.preventDefault();
                updateModal(boardId);
            };
        }(boardId);
    }

    function updateModal(boardId) {
        //TODO : 글 작성자가 아니어도 접근이 가능한 문제 해결 필요

        const htmlFilePath = '/board/toUpdateGatherBoardPage/' + boardId;

        asyncLoad(htmlFilePath);
    }

    let asyncForms = document.querySelectorAll(".asyncSubmit")
    asyncForms.forEach((form) => {
        form.addEventListener("submit", (event) => asyncSubmit(event, boardId, form));
    });
})();
