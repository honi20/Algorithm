#include <iostream>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string password, str;
    int n;
    vector <string> word;
    
    cin>>password>>n;
    for (int i=0; i<n; i++){
        cin>>str;
        word.push_back(str);
    }
    
    int tmp = 1;
    while (true){
        if (tmp > 26)
            break;
        
        // 암호 해독
        string result = "";
        for (int i=0; i<password.size(); i++){
            int realAlp = (password[i]-tmp);
            if (realAlp < (int)'a'){
                realAlp = (int)'z' - (int)'a' + realAlp + 1;
            }
            result += (char)(realAlp);
        }
//        cout<<result<<"*\n";
//        tmp++;
        
        bool flag = false;
        // 사전 단어 포함 되어 있는지 확인
        for (int i=0; i<word.size(); i++){
            string nowword = word[i];
            for (int j=0; j<result.size(); j++){
                // 시작 알파벳이 같은 경우
                if (nowword[0] == result[j]){
                    bool check = true;
                    int idx = j;
                    for (int k = 0; k<nowword.size(); k++){
                        if (nowword[k] != result[idx]){
                            check = false;
                            break;
                        }
                        idx++;
                    }
                    
                    if (check){
                        flag = true;
                        break;
                    }
                }
            }
            
            if (flag)
                break;
        }

        
        if (flag){
            cout<<result;
            break;
        }
        else
            tmp++;
        
    }
    
    return 0;
}
