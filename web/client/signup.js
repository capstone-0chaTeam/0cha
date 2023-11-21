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


      fetch("https://racconworld.com:18646/sign-up", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          password: password,
          nickname: nickname,
          email: id}
        )
      })
      .then((response)=>response.text())
      .then(data => {
        alert(data);
        if(data === "회원가입 성공"){
          alert("회원가입 되었습니다. \n 초기자금 5000만원이 지급되었습니다. \n 행운을 빕니다.")
          window.location.href ="https://capstone-0chateam.github.io/stocksimulation/login.html";
        } else {
          alert("회원가입 실패");
        }
    })


        .catch((error) => {
          // 오류 처리
          console.error('Fetch error:', error);
        });

  });
});
