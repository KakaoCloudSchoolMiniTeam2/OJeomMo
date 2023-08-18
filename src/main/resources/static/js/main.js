const menu = document.querySelector(".navbar_menu");

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
const createBtn = document.querySelector('.createBtn');
const modalContent = document.querySelector('.modal_body');
let dynamicScript = null; // 스크립트 엘리먼트를 저장할 변수

function removeDynamicScript() {
    if (dynamicScript) {
        document.head.removeChild(dynamicScript);
        dynamicScript = null;
    }
}

function asyncLoad(htmlFilePath, srcPath) {
    fetch(htmlFilePath)
        .then(response => response.text())
        .then(html => {
            modalContent.innerHTML = html;

            if(srcPath != null){
                dynamicScript = document.createElement('script');
                dynamicScript.src = '/js/'+srcPath+'.js';
                document.head.appendChild(dynamicScript);
            }

            const closeBtn = document.querySelector('.closeIconButton');
            closeBtn.onclick = closeModal;

            modal.classList.add('show');

            if (modal.classList.contains('show')) {
                body.style.overflow = 'hidden';
            }
        })
        .catch(error => {
            console.error('Error fetching HTML:', error);
        });
}

function getModal(boardId) {
    //여기부터 Ajax 추가하는 코드
    ////////////////////////////
    const htmlFilePath = '/board/readGatherBoard/' + boardId;

    asyncLoad(htmlFilePath,"gatherDetail");

}

async function asyncSubmit(event, boardId, form) {
    event.preventDefault();
    const formData = new FormData(form);
    const formAction = form.getAttribute('action');
    await fetch(formAction, {
        method: "POST",
        body: formData
    })

    getModal(boardId);
}

function createModal(event) {
    event.preventDefault();

    const htmlFilePath = '/board/readCreatePage';

    asyncLoad(htmlFilePath);
}

if (btnOpenPopup != null) {
    btnOpenPopup.forEach(postIt => {
        const boardId = postIt.id;
        postIt.onclick = () => getModal(boardId);
    })
}

if (modal != null) {
    modal.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.classList.toggle('show');

            if (!modal.classList.contains('show')) {
                body.style.overflow = 'auto';
            }
        }
    });
}

if (createBtn != null) {
    createBtn.onclick = createModal;
}

function closeModal(event) {
    event.preventDefault();
    console.log("ttt");
    body.style.overflow = 'auto';
    modal.classList.toggle('show');
    removeDynamicScript();
}

const logoutBtn = document.querySelector(".logoutBtn");

// 인증 토큰 삭제
function deleteCookie() {
    alert("로그아웃 되었습니다.");
    document.cookie = "Authorization" + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

if (logoutBtn != null) {
    logoutBtn.onclick = deleteCookie;
}