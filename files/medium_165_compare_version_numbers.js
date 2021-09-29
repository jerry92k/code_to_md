/**
 * @param {string} version1
 * @param {string} version2
 * @return {number}
 */
var compareVersion = function(version1, version2) {
    
    let ver1Split = version1.split('.');
    let ver2Split = version2.split('.');

    let i = 0;
    for (; i < ver1Split.length; i++){

        if (i > ver2Split.length - 1) { // ver2는 split한 단위가 끝남.
            if (Number(ver1Split[i]) != 0) {
                return 1;
            }
        } else {

            let ver1Num = Number(ver1Split[i]);
            let ver2Num = Number(ver2Split[i]);
          //  console.log(`here ver1 : ${ver1Num}, ver2: ${ver2Num}`);
            if (ver1Num > ver2Num) {
                return 1;
            }

            if (ver1Num < ver2Num) {
                return -1;
            }
            
        }
    }
    //ver1은 끝났는데 ver2 아직 있음. 
    for (; i < ver2Split.length; i++){
        if (Number(ver2Split[i]) != 0) {
            return -1;
        }
    }

    return 0;

};

console.log(compareVersion("1","1.1"));