#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
#include <queue>
#include <tuple>
#include <set>
#include <cmath>
using namespace std;
typedef long long ll;
typedef pair<int,int> pii;
#define MAX 100005
#define INF 987654321

int n, m;

pii solve(int n, int m){
    
    if (n == 2){
        switch(m){
            case 0:
                return {1,1};
            case 1:
                return {1,2};
            case 2:
                return {2,2};
            case 3:
                return {2,1};
        }
    }
    
    int half = n/2;
    int quadrant = m/(half*half);
    pii p;
    
    p = solve(half, m%(half*half));
    switch(quadrant){
        // 좌하단
        case 0:
            return {p.second, p.first};
            
        // 좌상단
        case 1:
            return {p.first, p.second+=half};
            
        // 우상단
        case 2:
            return {p.first+=half, p.second+=half};
            
        // 우하단
        case 3:
            return {2*half-p.second+1, half-p.first+1};
    }
    
    return {0,0};
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    pii result = solve(n, m-1);
    cout<<result.first<<" "<<result.second;
    return 0;
}

