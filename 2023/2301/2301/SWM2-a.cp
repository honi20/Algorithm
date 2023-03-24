#include <iostream>
#include <vector>
#include <cstring>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    string str;
    bool selAlp[26];
    int resultIdx[32];
    
    cin>>n;
    cin.ignore();
    
    for (int i=0; i<26; i++)
        selAlp[i] = false;
    
    for (int i=0; i<n; i++){
        getline(cin, str);
        
        resultIdx[i] = -1;
        bool checkFirst = true;
        bool flag = false;
        
        for (int j=0; j<str.size(); j++){
            if (flag)
                break;
            
            // 단어 앞글자 확인
            if (checkFirst){
                
                if ('a' <= str[j] && str[j] <= 'z'){
                    if (!selAlp[str[j] - 'a']){
                        selAlp[str[j] - 'a'] = true;
                        resultIdx[i] = j;
                        flag = true;
                        
                    }
                }
                
                else if ('A' <= str[j] && str[j] <= 'Z'){
                    if (!selAlp[str[j] - 'A']){
                        selAlp[str[j] - 'A'] = true;
                        resultIdx[i] = j;
                        flag = true;
                        
                    }
                }
                
                checkFirst = false;
            }
            
            // 한 단어 끝남
            if (str[j] == ' '){
                checkFirst = true;
            }
        }
        
        // 단어 앞글자에 없는 경우
        if (!flag){
            for (int j=0; j<str.size(); j++){
                if (flag)
                    break;
                
                if ('a' <= str[j] && str[j] <= 'z'){
                    if (!selAlp[str[j] - 'a']){
                        selAlp[str[j] - 'a'] = true;
                        resultIdx[i] = j;
                        flag = true;
                        
                    }
                }
                
                else if ('A' <= str[j] && str[j] <= 'Z'){
                    if (!selAlp[str[j] - 'A']){
                        selAlp[str[j] - 'A'] = true;
                        resultIdx[i] = j;
                        flag = true;
                        
                    }
                }
            }
        }
        if (resultIdx[i] == -1)
            cout<<str<<"\n";
        
        else{
            for (int j=0; j<str.size(); j++){
                if (j == resultIdx[i]){
                    cout<<"["<<str[j]<<"]";
                }
                else
                    cout<<str[j];
            }
            cout<<"\n";
        }
    }
}
