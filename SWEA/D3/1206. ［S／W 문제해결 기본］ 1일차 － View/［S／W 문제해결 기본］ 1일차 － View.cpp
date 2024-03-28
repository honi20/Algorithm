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
    
    int test_case;
    int T = 10;
    
    for(test_case = 1; test_case <= T; ++test_case)
    {
        int N;
        int arr[1002] = {0,};
        int result = 0;
        
        cin>>N;
        for (int i=0; i<N; i++){
            cin>>arr[i];
        }

        for (int i=2; i<N-2; i++) {
            int left = max(arr[i-1], arr[i-2]);
            int right = max(arr[i+1], arr[i+2]);
            int tmp = max(left, right);
            
            if (tmp < arr[i])
                result += (arr[i] - tmp);
        }
        cout<<"#"<<test_case<<" "<<result<<"\n";

    }
    return 0;
}
