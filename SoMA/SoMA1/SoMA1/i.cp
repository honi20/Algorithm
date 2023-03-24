#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include <queue>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    queue<int> q;
    
    cin>>n;
    for (int i=1; i<=n; i++)
        q.push(i);
    
    while (true){
        if (q.size() == 1){
            cout<<q.front();
            break;
        }
        
        q.pop();
        int tmp = q.front();
        q.pop();
        q.push(tmp);
    }
    
    
    return 0;
}
