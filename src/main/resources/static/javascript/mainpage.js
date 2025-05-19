document.addEventListener("DOMContentLoaded", function () {
  // 햄버거 버튼 클릭 이벤트
  const sidebarToggle = document.getElementById("sidebarToggle");
  const sidebar = document.getElementById("sidebar");
  const closeSidebar = document.getElementById("closeSidebar");
  const mainContent = document.querySelector("main");

  sidebarToggle.addEventListener("click", function () {
    sidebar.classList.add("active");
    mainContent.classList.add("sidebar-open");
  });

  closeSidebar.addEventListener("click", function () {
    sidebar.classList.remove("active");
    mainContent.classList.remove("sidebar-open");
  });

  // 로그인/회원가입 버튼 이벤트
  document.querySelector(".login-btn").addEventListener("click", function () {
    alert("로그인 페이지로 이동합니다.");
    // window.location.href = '/login';  // 실제 구현시 사용
  });

  document.querySelector(".vetsignup-btn").addEventListener("click", function () {
    alert("회원가입 페이지로 이동합니다.");
    // window.location.href = '/signup';  // 실제 구현시 사용
  });

  // 로그인 상태 시뮬레이션 (실제 구현시 서버에서 상태 확인)
//  let isLoggedIn = false; // 기본값 false로 설정
//
//  function toggleLoginView() {
//    const loggedInView = document.querySelector(".logged-in-view");
//    const loggedOutView = document.querySelector(".logged-out-view");
//
//    if (isLoggedIn) {
//      loggedInView.style.display = "block";
//      loggedOutView.style.display = "none";
//    } else {
//      loggedInView.style.display = "none";
//      loggedOutView.style.display = "block";
//    }
//  }

  // 초기 상태 적용
//  toggleLoginView();

   // 사이드바 로그인 버튼 이벤트
   document.querySelector('.login-sidebar-btn').addEventListener('click', function() {
       alert('로그인 페이지로 이동합니다.');
       // window.location.href = '/login';
   });

   document.querySelector('.signup-sidebar-btn').addEventListener('click', function() {
       alert('회원가입 페이지로 이동합니다.');
       // window.location.href = '/signup';
   });

   // (테스트용) 로그인 상태 변경 함수
   function simulateLogin() {
       isLoggedIn = true;
       toggleLoginView();
   }

   function simulateLogout() {
       isLoggedIn = false;
       toggleLoginView();
   }

  // 실제 구현시 예시: 로그인 성공 후 호출
  // simulateLogin();

  // 동물 카드 뒤집기 이벤트
  const petCards = document.querySelectorAll(".pet-card");
  petCards.forEach((card) => {
    card.addEventListener("click", function () {
      this.classList.toggle("flipped");
    });
  });

  // 메디컬/커뮤니티 탭 클릭 이벤트
  const medicalTab = document.querySelector(".medical");
  const communityTab = document.querySelector(".community");

  medicalTab.addEventListener("click", function () {
    alert("메디컬 페이지로 이동합니다.");
    // 실제 구현시 페이지 이동 코드로 변경
  });

  communityTab.addEventListener("click", function () {
    alert("커뮤니티 페이지로 이동합니다.");
    // 실제 구현시 페이지 이동 코드로 변경
  });

  // 검색 버튼 클릭 이벤트
  const searchBtn = document.querySelector(".search-btn");
  searchBtn.addEventListener("click", function () {
    const searchTerm = document.querySelector(".search-container input").value;
    if (searchTerm.trim() !== "") {
      alert(`"${searchTerm}" 검색 결과 페이지로 이동합니다.`);
      // 실제 구현시 검색 결과 페이지로 이동
    } else {
      alert("검색어를 입력해주세요.");
    }
  });

  // 검색창 엔터 키 이벤트
  const searchInput = document.querySelector(".search-container input");
  searchInput.addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
      searchBtn.click();
    }
  });

});

// 마이페이지 사용자 정보 수정 모달창 열기/ 닫기 및 초기화
document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("userEditModal");
    const editBtn = document.querySelector(".edit-btn");
    const closeBtn = document.querySelector(".close-modal");

    if (editBtn) {
        editBtn.addEventListener("click", () => {
            modal.style.display = "block";
        });
    }

    if (closeBtn) {
        closeBtn.addEventListener("click", () => {
            modal.style.display = "none";
            document.getElementById("userEditForm").reset(); // 내용 초기화
        });
    }

    window.addEventListener("click", function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
            document.getElementById("userEditForm").reset(); // 내용 초기화
        }
    });
});
