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
    
    int a, b;
    int k = 1;
    vector <int> v[20];
    bool check[20];
    int maxnum = 0;
    
    while (cin>>a>>b){
        if (a == -1 && b == -1)
            break;
        
        else if (a == 0 && b == 0){
            int rootcnt = 0;
            bool flag = true;
            for (int i=1; i<=maxnum; i++){
                if (check[i]){
                    // root node
                    if (v[i].size() == 0){
                        rootcnt++;
                        if (rootcnt > 1){
                            flag = false;
                            break;
                        }
                    }
                    
                    // child node has 2 parent node
                    else if (v[i].size() >= 2){
                        flag = false;
                        break;
                    }
                }
            }
            
            if (rootcnt == 0)
                flag = false;
            
            if (flag)
                cout<<"Case "<<k<<" is a tree.\n";
            else
                cout<<"Case "<<k<<" is not a tree.\n";
            k++;
            
            for (int i=1; i<=maxnum; i++){
                v[i].clear();
                check[i] = false;
            }
            maxnum = 0;
        }
        
        else{
            v[b].push_back(a);
            check[a] = check[b] = true;
            maxnum = max(max(maxnum, a), b);
        }
    }
    
}

