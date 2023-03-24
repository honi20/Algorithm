#include <iostream>
#include <set>
#include <algorithm>
#include <vector>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,m;
    string str;
    set<string> s;
    vector <string> result;
    
    cin>>n>>m;
    for (int i=0;i<n;i++){
        cin>>str;
        s.insert(str);
    }
    for (int i=0;i<m;i++){
        cin>>str;
        if (s.find(str) != s.end()){
            result.push_back(str);
        }
    }
    sort(result.begin(), result.end());
    cout<<result.size()<<"\n";
    for (int i=0;i<result.size();i++)
        cout<<result[i]<<"\n";
}
