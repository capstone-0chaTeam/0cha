// 
// const serverUrl = "http://localhost:8082/Id_NickName_Balance";
// URL이 "http://" 또는 "https://"로 시작하지 않으면 "http://"를 추가
// if (!serverUrl.startsWith("http://") && !serverUrl.startsWith("https://")) {
//     serverUrl = "http://" + serverUrl;
// }
// fetch(serverUrl, {

    const accesstoken = localStorage.getItem('accessToken');
    const serverUrl = "http://localhost:8082/userInfo/Id_NickName_Balance";
    //URL이 "http://" 또는 "https://"로 시작하지 않으면 "http://"를 추가
    if (!serverUrl.startsWith("http://") && !serverUrl.startsWith("https://")) {
        serverUrl = "http://" + serverUrl;
    }
    fetch(serverUrl, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBY2Nlc3NUb2tlbiIsImV4cCI6MTY5OTQ1NTM5NywiZW1haWwiOiIxMjMifQ.PYRKmnpf5zcR25D0kN9ka1y0nCNuE53bUHf-eqawCNb93EOlfDjmL9nrVpHuncBvENnDZTXoc1qdJhINtruZNw',
            // 'Authorization' : accesstoken,
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    
    .then(data => {
        //웹화면에 이름 
        console.log(data)
        // console.log(data.nickname)

        document.getElementById("homeNickname").textContent = data.nickname;
        document.getElementById("homeAccount").textContent = data.account;
        document.getElementById("homeBalance").textContent = data.balance;
        localStorage.setItem('account', data.account);
    })
    .catch(error => {
        console.error('회원정보 요청 중 오류 발생:', error);
    });

// function startGame() {
//     // 계좌번호를 가져오거나 사용자 정보를 생성
//    

//     // Unity 웹 플레이어로 계좌번호 전달
//     var unityObject = UnityObject2.instances[0];
//     if (unityObject) {
//         unityObject.getUnity().SendMessage("YourGameObjectName", "ReceiveAccountNumber", accountNumber);
//     }
// }




// // 게임 시작 버튼 클릭 시 실행되는 JavaScript 함수
// function startGame() {
//     // 계좌번호를 가져오거나 사용자 정보를 생성
//     var accountNumber = data.account; // 웹에서 가져온 사용자 정보

//     // Unity에서 사용자 정보를 받을 함수 호출
//     SendMessageToUnity("ReceiveAccountNumber", accountNumber);
// }

// // Unity로 데이터 전달하는 함수
// function SendMessageToUnity(functionName, message) {
//     if (typeof UnityLoader !== "undefined" && UnityLoader.SystemInfo) {
//         // Unity에 메시지를 전달
//         UnityLoader.SystemInfo.SendMessage(functionName, message);
//     }
// }