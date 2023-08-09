// const profileBtn = document.querySelector(".navbar_profileBtn");
const profile = document.querySelector(".navbar_profile");
// const menuBtn = document.querySelector(".navbar_menuBtn");
const menu = document.querySelector(".navbar_menu");

// profileBtn.addEventListener("click", ()=>{
//     profile.classList.toggle("active");
// });

// menuBtn.addEventListener("click", ()=>{
//     menu.classList.toggle("active");
// });

// 화면 크기 설정
const screenHeight = window.innerHeight;
document.body.style.height = `${screenHeight}px`;

// 사이드 바 관련
let toggleSideBar = (event) => {
    event.preventDefault();
    document.querySelector("#sidebar-wrapper").classList.toggle("active");
}
document.querySelector(".navbar_menuBtn").onclick = toggleSideBar;

let toggleProfile = (event) => {
    event.preventDefault();
    document.querySelector("#profile-wrapper").classList.toggle("active");
}
document.querySelector(".navbar_profileBtn").onclick = toggleProfile;

// 버튼 등장 미디어 쿼리관련
function activateSideInWideScreen() {
    const WIDTH = 1550
    const sidebar = document.querySelector("#sidebar-wrapper");
    const profile = document.querySelector("#profile-wrapper");
    const isMobile = window.matchMedia("(max-width: " + WIDTH + "px)").matches;

    if (isMobile) {
        sidebar.classList.remove('active');
        profile.classList.remove('active');
    } else {
        sidebar.classList.add('active');
        profile.classList.add('active');
    }
}

// 페이지 로드 시 초기 실행
activateSideInWideScreen();

// 윈도우 리사이즈 시 실행
window.addEventListener('resize', activateSideInWideScreen);

// modal 창띄우기
const body = document.querySelector('body');
const modal = document.querySelector('.modal');
const btnOpenPopup = document.querySelectorAll('.postit');

function getModal(postit) {

    const modalContent = document.querySelector('.modal_body')

    //여기부터 Ajax 추가하는 코드
    ////////////////////////////
    const boardId = postit.id;
    const htmlFilePath = '/board/readGatherBoard/' + boardId;

    fetch(htmlFilePath)
        .then(response => response.text())
        .then(html => {
            modalContent.innerHTML = html;
            const closeBtn = document.querySelector('.detailCloseIconButton');
            closeBtn.onclick = closeModal;
            const deleteBtn = document.querySelector("[name = boardDeleteBtn]");
            deleteBtn.onclick = deleteAlert;
            const updateBtn = document.querySelector("[name = boardUpdateBtn]");
            updateBtn.onclick = function(boardId) {
                return function (event){
                    event.preventDefault();
                    updateModal(boardId);
                };
            }(boardId);
        })
        .catch(error => {
            console.error('Error fetching HTML:', error);
        });

    modal.classList.add('show');

    if (modal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
}

function updateModal(boardId){
    //TODO : 글 작성자가 아니어도 접근이 가능한 문제 해결 필요
    const modalContent = document.querySelector('.modal_body')

    const htmlFilePath = '/board/toUpdateGatherBoardPage/'+boardId;
    
    fetch(htmlFilePath)
        .then(response => response.text())
        .then(html => {
            modalContent.innerHTML = html;
            const closeBtn = document.querySelector('.detailCloseIconButton');
            closeBtn.onclick = closeModal;
        })
        .catch(error => {
            console.error('Error fetching HTML:', error);
        });

    modal.classList.add('show');

    if (modal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
}

btnOpenPopup.forEach(postit => {
    console.log(postit);
    postit.onclick = () => getModal(postit);
});

modal.addEventListener('click', (event) => {
    if (event.target === modal) {
        modal.classList.toggle('show');

        if (!modal.classList.contains('show')) {
            body.style.overflow = 'auto';
        }
    }
});

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

function closeModal() {
    modal.classList.toggle('show');
}

// 인증 토큰 삭제
function deleteCookie() {
    alert("요청 처리됨");
    document.cookie = "Authorization" + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

if (document.querySelector(".logoutBtn") != null) {
    document.querySelector(".logoutBtn").onclick = deleteCookie;
}

