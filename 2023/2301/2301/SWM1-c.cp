#include <iostream>
#include <vector>
#include <map>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    string str;
    map <string, int> carNum;
    int carIdx[1002] = {0,};
    bool carResult[1002] = {false,};
    
    cin>>n;
    for (int i=1; i<=n; i++){
        cin>>str;
        carNum[str] = i;
    }
    
    for (int i=0; i<n; i++){
        cin>>str;
        carIdx[i] = carNum[str];
        
        for (int j=0; j<i; j++){
            if (carIdx[j] > carIdx[i])
                carResult[j] = true;
        }
    }
    
    int resultCnt = 0;
    for (int i=0; i<n; i++){
        if (carResult[i]){
            resultCnt++;
        }
    }
    cout<<resultCnt;
}
