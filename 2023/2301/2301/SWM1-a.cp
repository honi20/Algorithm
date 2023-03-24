#include <iostream>
#include <vector>
#include <set>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string str, puzzle;
    vector <string> testword;
    int alpCnt[26][2] = {0,};      // [0] : 퍼즐 원래 개수 , [1] : 예제 가능 여부 확인
    int result[26] = {0,};
    
    while (true){
        cin>>str;
        if (str == "-")
            break;
        
        testword.push_back(str);
    }
    
    while (true){
        cin>>puzzle;
        
        if (puzzle == "#")
            break;
        
        // 퍼즐판 구성 알파벳 개수 초기화
        for (int i=0; i<26; i++)
            alpCnt[i][0] = alpCnt[i][1] = result[i] = 0;
        
        // 퍼즐판 구성 알파벳 개수 확인
        for (int i=0; i<puzzle.size(); i++)
            alpCnt[puzzle[i] - 'A'][0]++;
        
        // 예제 단어 확인
        for (int i=0; i<testword.size(); i++){
            for (int i=0; i<26; i++)
                alpCnt[i][1] = alpCnt[i][0];
            
            bool flag = true;
            for (int j=0; j<testword[i].size(); j++){
                int nowAlp = testword[i][j] - 'A';
                // 가능하지 않은 단어인 경우
                if (alpCnt[nowAlp][1] <= 0){
                    flag = false;
                    break;
                }
                
                alpCnt[nowAlp][1]--;
            }
            
            // 해당 단어 불가
            if (!flag)
                continue;
            
            // 해당 단어 가능
            else{
                for (int k=0; k<26; k++){
                    if (alpCnt[k][0] > 0){
                        for (int j=0; j<testword[i].size(); j++){
                            int nowAlp = testword[i][j] - 'A';
                            if (k == nowAlp){
                                result[k]++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        // 최소, 최대 알파벳 출력
        int minval = 200002;
        string minresult = "";
        int maxval = 0;
        string maxresult = "";
        
        for (int i=0; i<26; i++){
//            cout<<(char)(i+'A')<<" : "<<result[i]<<"\n";
            if (alpCnt[i][0] > 0  && result[i] <= minval){
                if (result[i] < minval){
                    minval = result[i];
                    minresult = "";
                    minresult += (i+'A');
                }

                else if (result[i] == minval)
                    minresult += (i+'A');
            }

            if (alpCnt[i][0] > 0 && result[i] >= maxval){
                if (result[i] > maxval){

                    maxval = result[i];
                    maxresult = "";
                    maxresult += (i+'A');
                }

                else if (result[i] == maxval)
                    maxresult += (i+'A');
            }
        }
        cout<<minresult<<" "<<minval<<" "<<maxresult<<" "<<maxval<<"\n";
    }
}
