#include <iostream>
#include <map>
#include <cstring>
using namespace std;
typedef long long ll;

int dx[4] = {1,0,-1,0};
int dy[4] = {0,-1,0,1};

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    int m, n, q;
    int dir = 0;
    int nx = 0, ny = 0;
    
    string p;
    
    cin>>m>>n;
    
    bool check = true;
    while (n--){
        cin>>p>>q;
        
        if (!check)
            continue;
        
        if (p == "TURN"){
            if (q == 0){
                dir--;
                if (dir < 0)
                    dir = 3;
            }
            else if (q == 1){
                dir++;
                if (dir == 4)
                    dir = 0;
            }
        }
        
        else if (p == "MOVE"){
            nx = nx + dx[dir]*q;
            ny = ny + dy[dir]*q;
            
            if (ny > m || nx > m || ny < 0 || nx < 0){
                check = false;
            }
            
        }
    }
    if (check)
        cout<<nx<<" "<<ny;
    else
        cout<<"-1";
}
