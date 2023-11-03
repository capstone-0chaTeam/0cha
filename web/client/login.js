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

                const emailInput = document.getElementById('email');
                const passwordInput = document.getElementById('password');

                if (checkNullInput(emailInput) && checkNullInput(passwordInput)) {
                    const email = emailInput.value;
                    const password = passwordInput.value;

                    fetch('${backend_base_url}/login', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            email: email,
                            password: password
                        })
                    })
                    .then(response => {
                            console.log(response.data);
                        console.log(response.status);
                        console.log(email);
                        console.log(password);
                        if (response.ok) { // HTTP 상태 코드가 200일 경우 
                            console.log("서버에 제대로 전달했는지 로그 찍어서 확인"+response);
                            console.log(response.headers);
                            accessToken = response.headers.get('Authorization');
                            refreshToken = response.headers.get('Authorization-Refresh');
                            localStorage.setItem('Authorization', accessToken);
                            localStorage.setItem('Authorization-Refresh', refreshToken);
                          

                            if (response.headers.get('Authorization') === null) {
                                alert("아이디 혹은 비밀번호가 틀렸습니다.");
                                return;
                            }
                            window.location.href = '${frontend_base_url}/mainSuccess.html';
                        } else {
                            alert("이메일 또는 비밀번호가 잘못되었습니다.");
                        }
                    })
                    .catch(error => {
                        console.error(error);
                    });
                } else {
                    // 아이디나 비밀번호 중 하나라도 입력되지 않았을 경우 처리
                    const emptyField = emailInput.value === '' ? emailInput : passwordInput;
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