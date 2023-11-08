const frontend_base_url = "http://127.0.0.1:5500"
const backend_base_url = "http://localhost:8082"



window.onload = () => {
  console.log("로딩되었음")
}

document.addEventListener("DOMContentLoaded", function() {
    const signupForm = document.getElementById("signup-form");
    const messageDiv = document.getElementById("message");

    signupForm.addEventListener("submit", function(event) {
        event.preventDefault();
        
        const password = document.getElementById("password").value;
        const nickname = document.getElementById("nickname").value;
        const id = document.getElementById("id").value;

        const data = {
          password: password,
          nickname: nickname,
          email: id
        }; 

        fetch("http://localhost:8082/sing-up", {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(data),
        })
        .then((response)=>response.json())
        .then((response) => {
          console.log(response)
          if (response.status === 200) {
            alert("회원가입 되었습니다. \n 초기자금 5000만원이 지급되었습니다. \n 행운을 빕니다.")
            window.location.href ="http://127.0.0.1:5500/login.html";
          } else {
            alert("회원가입 실패");
            return response.json();
          }
        })
        .then((responseData) => {
          
            console.log("로딩되었음" + responseData)
        
            let rows = responseData['id'];
            for (let i = 0; i < rows.length; i++) {
              let alreadyId = rows[i]['id'];
              if (alreadyId === document.querySelector('#id').value) {
                alert( '중복된 아이디입니다.');
              } else {
              alert('사용가능한 아이디입니다');
              }
            }
          })
          .catch((error) => {
            // 오류 처리
            console.error('Fetch error:', error);
          });

    });
});
 