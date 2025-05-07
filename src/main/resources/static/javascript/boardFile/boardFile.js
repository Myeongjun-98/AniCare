document.querySelectorAll(".form-control").forEach(function(fileInput){
    fileInput.addEventListener('change', function(event){
    //게시글 작성 - 첨부파일 입력란에서 파일이 입력되었을 때 작동되는 함수
    //용도: 확장자가 이미지인지 아닌지 확인해보는 js
    //나중에 동영상도 할 수 있음 구현해보기.

        const file = event.target.files[0];
        const tempUrl = URL.createObjectURL(file);

    //this = fileInput에 들어간 값
        this.nextElement.src = tempUrl;

        //사용자의 파일명 뽑아내기
        //ex) C:\anicare\community\image.png 를 \를 기준으로 나눔. ["anicare", "community", "image.png"]
        //.pop() -> 배열의 마지막 요소 꺼내기 = "image.png"
        // ?: '\' 자체가 특수문자가 시작되는 자바스크립트의 문자이기 때문에, 저거 하나만 쓰면 오류남.
        // '\\' 이렇게 두 번 써야 실제로 작동될 때에는 백슬라이스(\) 하나로 적용되는 것이다.
        const fileName = this.value.split("\\").pop();

        //jpg, png 등의 파일 확장자 텍스트 꺼내오기
        //["image", "png"] -> "png"
        //.toLowerCase() -> 전부 소문자로 변환.
        const fileExt = fileName.split(".").pop().toLowerCase();

        //이미지인지 확인 -> 아닐시 alert 띄우기
        if(fileExt !== "jpg" && fileExt !== "png" && fileExt !== "gif" && fileExt !== "bmp" && fileExt !== "jpeg"){
            alert("이미지만 등록 가능합니다.");
            return;
        }

        this.previousElementSibling.textContent = fileName;

        });
     });
