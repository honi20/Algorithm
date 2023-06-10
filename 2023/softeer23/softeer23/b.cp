#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
#include <map>
#include <queue>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    string str;
    char alp;
    map<char, int> alpCnt;
    vector <char> arr;
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>str;
        if (str == "enqueue"){
            cin>>alp;
            arr.push_back(alp);
            
            if (alpCnt.find(alp) == alpCnt.end())
                alpCnt[alp] = 1;
            else
                alpCnt[alp]++;
        }
        
        else{
            char maxAlp;
            int maxCnt = 0;
            for (auto iter = alpCnt.begin(); iter != alpCnt.end(); iter++){
                if (iter -> second > maxCnt){
                    maxCnt = iter -> second;
                    maxAlp = iter -> first;
                }
            }
            
            alpCnt[maxAlp]--;
        }
    }
}

