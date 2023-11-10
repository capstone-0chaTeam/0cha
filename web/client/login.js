const frontend_base_url = "http://127.0.0.1:5500"
const backend_base_url = "http://localhost:8082"

window.onload = () => {
    console.log("로딩되었음")
}
   

 document.addEventListener("DOMContentLoaded", function() {
            const loginForm = document.querySelector("form");
            const messageDiv = document.getElementById("message");

            loginForm.addEventListener("submit", function(event) {
                event.preventDefault();

                const idInput = document.getElementById('id');
                const passwordInput = document.getElementById('password');

                if (checkNullInput(idInput) && checkNullInput(passwordInput)) {
                    const id = idInput.value;
                    const password = passwordInput.value;




                    fetch("http://localhost:8082/login", {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                
                        },
                        body: JSON.stringify({
                            email: id,
                            password: password
                        })
                    })
                    .then(response => {
                            console.log(response.data);
                        console.log(response.status);
                        console.log(password);
                        if (response.ok) { // HTTP 상태 코드가 200일 경우 
                            console.log("서버에 제대로 전달했는지 로그 찍어서 확인"+response);
                            console.log(response.headers);
                            accessToken = response.headers.get('Authorization');
                            refreshToken = response.headers.get('Authorization-Refresh');
                         
                            if (response.headers.get('Authorization') === null) {
                                alert("아이디 혹은 비밀번호가 틀렸습니다.");
                                return;
                            }

                            localStorage.setItem('accessToken', accessToken);
                            localStorage.setItem('Authorization-Refresh', refreshToken);
                


                            console.log("로그인 완료. 메인화면으로 넘어갑니다")
                            window.location.href = "http://127.0.0.1:5500/mainSuccess.html";
                        } else {
                            alert("아이디 또는 비밀번호가 잘못되었습니다.");
                            return;
                        }
                    })
                    .catch(error => {
                        console.error(error);
                    });




                } else {
                    // 아이디나 비밀번호 중 하나라도 입력되지 않았을 경우 처리
                    const emptyField = idInput.value === '' ? idInput : passwordInput;
                    emptyField.focus();
                }
            });
        });

        function checkNullInput(input) {
            if (input.value === "") {
                console.log('로그인 정보 미입력');
                return false;
            } else {
                console.log('로그인 정보 입력');
                return true;
            }
        }


        