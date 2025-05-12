document.addEventListener('DOMContentLoaded', function() {
    // 페이지 진입 시 알림 표시
    alert('문의사항 제목과 내용엔 개인정보 사용은 지양해 주시기 바랍니다');

    // 글자 수 카운팅
    const contentTextarea = document.getElementById('content');
    const charCount = document.getElementById('currentCount');

    contentTextarea.addEventListener('input', function() {
        charCount.textContent = this.value.length;
    });

    // 파일 업로드 시 파일명 표시
    const fileInput = document.getElementById('attachment');
    const fileNameSpan = document.getElementById('fileName');

    fileInput.addEventListener('change', function() {
        if (this.files.length > 0) {
            fileNameSpan.textContent = this.files[0].name;

            // 파일 1개 제한
            if (this.files.length > 1) {
                alert('파일은 1개만 첨부 가능합니다');
                this.value = '';
                fileNameSpan.textContent = '선택된 파일 없음';
            }

            // 이미지 파일만 허용
            const file = this.files[0];
            if (!file.type.match('image.*')) {
                alert('이미지 파일만 첨부 가능합니다');
                this.value = '';
                fileNameSpan.textContent = '선택된 파일 없음';
            }
        } else {
            fileNameSpan.textContent = '선택된 파일 없음';
        }
    });

    // 폼 제출 처리
    const enquiryForm = document.getElementById('enquiryForm');

    enquiryForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const enquiryType = document.getElementById('inquiryType').value;
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;

        // 필수 필드 검증
        if (!enquiryType || !title || !content) {
            alert('필수 항목을 모두 입력해주세요');
            return;
        }

        // 실제 구현시 AJAX 요청으로 대체
        console.log('문의사항 제출:', {
            type: enquiryType,
            title: title,
            content: content,
            attachment: fileInput.files[0]?.name || '없음'
        });

        alert('문의사항이 등록되었습니다.\n관리자 검토 후 답변드리겠습니다.');

        // 문의 목록 페이지로 리다이렉트
        // window.location.href = 'enquiry-list.html';
    });

    // 뒤로 가기 버튼
    document.querySelector('.hamburger').addEventListener('click', function(e) {
        if (confirm('작성 중인 내용이 사라집니다. 취소하시겠습니까?')) {
            window.history.back();
        }
    });
});