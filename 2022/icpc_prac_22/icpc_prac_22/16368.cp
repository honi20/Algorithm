#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
#include <queue>
#include <tuple>
using namespace std;
typedef long long ll;
typedef tuple<int,int,int> tii;

int m,n,w,h;
int days, pers;

priority_queue<tii, vector<tii>> pq;     // {일을 하고있는지 여부, 남은 일의 수, 번호}
int startDay[2002]={0,};        // 일 또는 휴식 시작한 날
queue <tii> q;                  // 각 날짜에 일을 할 수 있는지 확인한 사람들

vector <int> result[2002];

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    cin>>m>>n>>w>>h;
    for (int i=1; i<=m; i++){
        cin>>days;
        pq.push({0, days, i});
    }
    
    for (int i=1; i<=n; i++){
        cin>>pers;
        
        int cnt = 0;       // 선택된 사람 수
        while (true){
            
            // 종료 조건
            if (pq.empty())
                break;
            
            if (cnt == pers)
                break;
            
            // ~~~~
            int workCheck = get<0>(pq.top());
            int workDays = get<1>(pq.top());
            int perNum = get<2>(pq.top());
            pq.pop();
            
//            cout<<workCheck<<" "<<workDays<<" "<<perNum<<"\n";
            
            if (workDays <= 0)
                continue;
            
            // 일을 하고있던 사람
            if (workCheck == 1){
                
                // 휴식 시작
                if (i - startDay[perNum] >= w){
                    q.push({0, workDays, perNum});
                    startDay[perNum] = i;
//                    cout<<"restStart! : "<<perNum<<"\n";
                }
                
                // 계속 일
                else{
                    q.push({workCheck, workDays-1, perNum});
                    cnt++;
//                    cout<<"workContinue! : "<<perNum<<"\n";
                }
            }
            
            // 휴식을 하고있던 사람
            else{
                // 일 시작
                if (i - startDay[perNum] >= h || startDay[perNum] == 0){
                    q.push({1, workDays-1, perNum});
                    result[perNum].push_back(i);
                    startDay[perNum] = i;
                    cnt++;
//                    cout<<"workStart! : "<<perNum<<"\n";
                }
                
                // 계속 휴식
                else{
                    q.push({workCheck, workDays, perNum});
//                    cout<<"restContinue! : "<<perNum<<"\n";
                }
            }
        }
        
        if (cnt != pers){
            cout<<"-1";
            return 0;
        }
        
        while (!q.empty()){
            pq.push({get<0>(q.front()), get<1>(q.front()), get<2>(q.front()) });
            q.pop();
        }
    }

//    while (!pq.empty()){
//        int tmp = get<1>(pq.top());
//        if (tmp > 0){
//            cout<<"-1";
//            return 0;
//        }
//        pq.pop();
//    }
    
    for (int i=1;i<=m;i++){
        for (int j=0;j<result[i].size();j++)
            sort(result[i].begin(), result[i].end());
    }
    
    cout<<"1\n";
    for (int i=1;i<=m;i++){
        for (int j=0;j<result[i].size();j++)
            cout<<result[i][j]<<" ";
        cout<<"\n";
    }
    
    return 0;
}
