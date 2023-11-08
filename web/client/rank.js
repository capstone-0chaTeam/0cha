const frontend_base_url = "http://127.0.0.1:5500"
const backend_base_url = "http://localhost:8082"
const token1 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBY2Nlc3NUb2"
// http://localhost:8082/userInfo/rank
fetch("http://localhost:8082/userInfo/rank", {
    method: 'GET',
    headers: {
        'Authorization': token1
    }
})
.then(response => response.json())
.then(data => {
   topRanking(data);
   ranking(data);
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