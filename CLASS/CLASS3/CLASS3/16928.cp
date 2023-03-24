#include <iostream>
#include <map>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int  n,m, x, y;
    map <int,int> g1;
    map <int,int> g2;
    int pos = 1;
    int cnt = 0;
    
    cin>>n>>m;
    
    for (int i=0;i<n;i++){
        cin>>x>>y;
        g1[x]=y;
    }
    
    for (int i=0;i<m;i++){
        cin>>x>>y;
        g2[x]=y;
    }
    
    while (true){
        int nextpos=0;
        
        for (int k=1;k<=6;k++){
            if (g1.find(pos+k) != g1.end()){    // 뱀 있는 칸
                nextpos = max(nextpos, g1[pos+k]);
            }
            else if (g2.find(pos+k) != g2.end()){   // 사다리 있는 칸
                nextpos = max(nextpos, g2[pos+k]);
            }
            else{
                nextpos = max(nextpos, pos+k);
            }
        }
        cnt++;
        
        if (nextpos >= 100)
            break;
        
        else
            pos = nextpos;
    }
    cout<<cnt;
}
