#include <iostream>
#include <vector>
#include <set>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    string str;
    vector <string> stnum;
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>str;
        stnum.push_back(str);
    }
    
    int s = 1;
    int e = 100;
    int result = 101;
    
    while (s <= e){
        int mid = (s+e)/2;
        set<string> ableNum;
        bool flag = true;
        
        for (int i=0; i<n; i++){
            long long stsize = stnum[i].size();
            string midNum = "";
            
            for (long long j=stsize-mid; j<stsize; j++){
                midNum += stnum[i][j];
            }
            
            if (ableNum.find(midNum) == ableNum.end()){
                ableNum.insert(midNum);
            }
            else{
                flag = false;
                break;
            }
        }
        
        if (flag){
            result = mid;
            e = mid - 1;
        }
        else
            s = mid + 1;
    }
    cout<<result;
}
