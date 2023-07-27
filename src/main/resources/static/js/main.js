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

// 인증 토큰 삭제
function deleteCookie() {
    alert("요청 처리됨");
    document.cookie =  "Authorization" + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

document.querySelector(".logoutBtn").onclick = deleteCookie;