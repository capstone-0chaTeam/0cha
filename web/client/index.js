window.onload = () => {
    console.log("로딩되었음")
}
   

var currentURL = window.location.href;

if (currentURL.includes('?')) {
// // 현재 페이지 URL을 가져옵니다.

// // URL에서 쿼리 문자열을 추출합니다.
var queryString = currentURL.split('?')[1];

// // 쿼리 문자열을 파싱하여 키-값 쌍을 객체로 변환합니다.
var queryParams = {};
queryString.split('&').forEach(function(param) {
    var parts = param.split('=');
    var key = decodeURIComponent(parts[0]);
    var value = decodeURIComponent(parts[1]);
    queryParams[key] = value;
});

// // accessToken 및 refreshToken을 추출합니다.
var accessToken = queryParams.accessToken;
var refreshToken = queryParams.refreshToken;

if (accessToken && refreshToken) {
    // localStorage에 저장
    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('refreshToken', refreshToken);
    refreshPage();
}
// 페이지 새로 고침// 다른 페이지로 리디렉션
}

function refreshPage() {
    window.location.href = "https://capstone-0chateam.github.io/stocksimulation/mainSuccess.html"
}

   
//------------------------랭킹-------------------------

//     fetch("https://racconworld.com:18646/userInfo/rank", {
//     method: 'GET'
// })


const token1 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBY2Nlc3NUb2"
fetch("https://racconworld.com:18646/userInfo/rank", {
    method: 'GET',
    headers: {
        'Authorization': token1
    }
})



.then(response => response.json())
.then(data => {
   topRanking(data);
   ranking(data);
   infoRanking(data);
})
.catch(error => {
    console.error('랭킹 정보 요청 중 오류 발생:', error);
});

function topRanking(data){
     document.getElementById("nickname1").textContent = data[0].nickname;
     document.getElementById("account1").textContent = data[0].balance +" 원";
 
     document.getElementById("nickname2").textContent = data[1].nickname;
     document.getElementById("account2").textContent = data[1].balance+" 원";
 
     document.getElementById("nickname3").textContent = data[2].nickname;
     document.getElementById("account3").textContent = data[2].balance+" 원";
};
function ranking(data){
    const table= document.getElementById("rankDB");
    const tbody = table.querySelector('tbody');
    data.forEach((data, index) => {
        const row = document.createElement('tr');

        const rankCell = document.createElement('td');
        rankCell.textContent = index + 1;
        row.appendChild(rankCell);

        const nicknameCell = document.createElement('td');
        nicknameCell.textContent = data.nickname;
        row.appendChild(nicknameCell);

        const accountCell = document.createElement('td');
        accountCell.textContent = data.balance + " 원";
        row.appendChild(accountCell);

        tbody.appendChild(row);
})};
function infoRanking(data) {
    const savedDataString = localStorage.getItem('accountNumber');

    for (let i = 0; i < data.length; i++) {
        if (data[i].account === savedDataString) {
            document.getElementById("homeRanking").textContent = i+1;
            return;  // 해당 계정을 찾으면 바로 함수를 종료하도록 수정
        }
    }
}
