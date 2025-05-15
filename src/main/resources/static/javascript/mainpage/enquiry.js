

  // 내 문의사항 리스트 구현

  document.addEventListener('DOMContentLoaded', function() {
  // 실제 구현시 API 호출로 데이터 로딩
  // fetch('/api/my-enquiries')
  //     .then(response => response.json())
  //     .then(data => renderEnquiries(data));

  // 페이지네이션 버튼 이벤트
  document.querySelectorAll('.page-btn:not(:disabled)').forEach(btn => {
  btn.addEventListener('click', function() {
  if (!this.classList.contains('active')) {
  document.querySelector('.page-btn.active').classList.remove('active');
  this.classList.add('active');
  // 페이지 변경 로직 추가
  }
  });
  });

  // 문의글 클릭 이벤트 (테이블 행 전체 클릭 가능)
  document.querySelectorAll('tbody tr').forEach(row => {
  row.addEventListener('click', function() {
  // 상세 페이지로 이동 (이미 HTML에 onclick 설정됨)
  });
  });

  // 새 문의 작성 버튼 이벤트
  document.querySelector('.new-enquiry-btn').addEventListener('click', function(e) {
  // 이미 HTML에 href로 연결됨
  });

  // 로그아웃 버튼 이벤트
  document.querySelector('.logout-btn').addEventListener('click', function() {
  if (confirm('로그아웃 하시겠습니까?')) {
  // 로그아웃 처리
  window.location.href = 'index.html';
  }
  });
  });

  // (예시) 데이터 렌더링 함수
  function renderEnquiries(data) {
  const tbody = document.querySelector('tbody');
  tbody.innerHTML = '';

  data.forEach((enquiry, index) => {
  const row = document.createElement('tr');
  row.innerHTML = `
  <td>${data.length - index}</td>
  <td class="title-cell">${enquiry.title}</td>
  <td>${new Date(enquiry.createdAt).toLocaleDateString()}</td>
  <td><span class="status-badge ${getStatusClass(enquiry.status)}">${getStatusText(enquiry.status)}</span></td>
  `;
  row.addEventListener('click', () => {
  window.location.href = `enquiry-detail.html?id=${enquiry.id}`;
  });
  tbody.appendChild(row);
  });
  }

  function getStatusClass(status) {
  switch(status) {
  case 'completed': return 'completed';
  case 'in_progress': return 'in-progress';
  default: return 'pending';
  }
  }

  function getStatusText(status) {
  switch(status) {
  case 'completed': return '완료';
  case 'in_progress': return '처리중';
  default: return '미완료';
  }
  }


