/**
 * @param {string} s
 * @return {number}
 */
var numDecodings = function (s) {
    
    
    let sCountArray = [];
    let sLen = s.length;

    if (s[0] == '0') {
        return 0;
    }
    sCountArray.push(1);

    for (let i = 1; i < sLen; i++) {
        let num = Number(s[i]);

        if (num == 0) { // i 번째 숫자가 0인 경우,
            if ((Number(s[i - 1]) <= 0 || Number(s[i - 1]) > 2)) { // 앞의 숫자가 1,2가 아닌경우
                return 0;
            }
            // 20124
            sCountArray.push(sCountArray[i - 1]);
            //1 혹은 2면 promise
        }
        else { // i번째 숫자가 0이 아닌경우,

            let preNum = Number(s[i - 1]);

            if ((preNum == 1) || (preNum == 2 && num <= 6)) { // 십의자리로 변환 가능한 숫자이면 
                if (i + 1 < sLen && Number(s[i + 1]) == 0) { // 뒤에 0이 있는 경우엔 이 앞의 숫자와 십의자리 매칭을 하면 안되므로
                    sCountArray.push(sCountArray[i - 1]);
                }
                else {
                    if (i - 2 >= 0) {
                        let twoPreNum = Number(s[i - 2]);
                        if (twoPreNum == 1 || twoPreNum == 2) {
                            sCountArray.push(sCountArray[i - 1] + sCountArray[i - 2]); // sCountArray[i-2]*2 + sCountArray[i-1] - sCountArray[i-2]
                            // [i-2]번째까지 경우의 수는, i번째가 i-1번째와 십의자리 매칭을 하냐 안하냐의 따라서 경우가 나눠지므로 : sCountArray[i-2]*2
                            // [i-2]번째까지 경우의 수는에는 [i-2]번째와 [i-1]번째가 결합한 경우의 수가 제외되어있기 때문에 추가 고려해준다: sCountArray[i-1] - sCountArray[i-2]
                        }
                        else {
                            sCountArray.push(sCountArray[i - 2] * 2);
                        }     
                    }
                    else {
                        sCountArray.push(2);
                    }
                }
            }
            else {
                sCountArray.push(sCountArray[i - 1]);
            }
        }
    }
    return sCountArray[sLen - 1];
        
};