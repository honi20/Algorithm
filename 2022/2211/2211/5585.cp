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
    
    int n;
    cin>>n;
    int temp = 1000 - n;
    int arr[6] = {500,100,50,10,5,1};
    int result = 0;
    
    for (int k=0; k<6; k++){
        while (temp >= arr[k]){
            result++;
            temp-=arr[k];
        }
    }
    cout<<result;
}

