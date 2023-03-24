#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, x, y;
    vector <int> v;
    
    cin>>n;
    for (int i=1; i<=n; i++)
        v.push_back(i);

    sort(v.begin(), v.end());

    cin>>x;
    if (x == 1){
        cin>>y;
        int cnt = 0;
        do {
            cnt++;
            
            if (cnt == y){
                for (int i=0; i<v.size(); i++)
                    cout<<v[i]<<" ";
                cout<<"\n";
                break;
            }
        } while (next_permutation(v.begin(), v.end()));
    }
    
    else if (x == 2){
        vector <int> tmp;
        int cnt = 0;
        
        for (int i=0; i<n; i++){
            cin>>y;
            tmp.push_back(y);
        }
        
        do {
            bool flag = true;
            cnt++;
            for (int i=0; i<v.size(); i++){
                if (v[i] != tmp[i]){
                    flag = false;
                    break;
                }
            }
            
            if (flag){
                cout<<cnt;
                break;
            }
            
        } while (next_permutation(v.begin(), v.end()));
        
    }
    
}

