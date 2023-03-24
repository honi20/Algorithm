#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <map>
#include <set>
using namespace std;
typedef long long ll;
#define MAX 1000000001

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n;
    queue<int> q;
    
    cin>>n;
    for (int i=1;i<=n;i++)
        q.push(i);
    
    while (q.size()>1){
        q.pop();
        int temp = q.front();
        q.pop();
        q.push(temp);
    }
    cout<<q.front();
}
