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

int n;
char a, b, c;
int arr[30][2];     // [0] : left , [1] : right

void presolve(int node){
    if (node == 100)
        return;
    
    cout<<(char)(node+'A');
    presolve(arr[node][0]);
    presolve(arr[node][1]);
}

void insolve(int node){
    if (node == 100)
        return;
    
    insolve(arr[node][0]);
    cout<<(char)(node+'A');
    insolve(arr[node][1]);
}

void postsolve(int node){
    if (node == 100)
        return;
    
    postsolve(arr[node][0]);
    postsolve(arr[node][1]);
    cout<<(char)(node+'A');
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>a>>b>>c;
        int tmpA = a - 'A';
        int tmpB = b - 'A';
        int tmpC = c - 'A';
        
        if (tmpB == ('.'-'A'))
            arr[tmpA][0] = 100;
        else
            arr[tmpA][0] = tmpB;
        
        if (tmpC == ('.'-'A'))
            arr[tmpA][1] = 100;
        else
            arr[tmpA][1] = tmpC;
        
//        cout<<tmpA<<" : "<<arr[tmpA][0]<<" "<<arr[tmpA][1]<<"\n";
    }
    presolve(0);
    cout<<"\n";
    insolve(0);
    cout<<"\n";
    postsolve(0);
}

