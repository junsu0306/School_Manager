package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentSubjectRecyclerviewBinding

class SubjectRecyclerviewFragment : Fragment() {

    val subjects = arrayOf(
        SubjectInfo("프로그래밍 입문", "1학년", "C언어의 기본 구조와 문법을 배우는 과목. 실행하고자 하는 프로그램을 컴퓨터가 이해할 수 있는 언어로 만드는 과정을 C언어를 통해 시작한다."),
        SubjectInfo("객체지향프로그래밍", "2학년", "큰 단위의 프로그래밍을 잘 짤 수 있는 방법에 대하여 배우는 과목. 큰 규모의 프로그램을 개발, 관리, 유지보수 해야 하는 때를 위해 가장 유용하다고 알려진 객체지향 프로그래밍에 대해 배운다."),
        SubjectInfo("컴퓨터구조론", "2학년", "컴퓨팅의 기본 원리를 이해할 수 있도록 돕는 기본 과목. 소프트웨어 개발자의 관점에서 컴퓨터구조/시스템 설계 및 동작 원리 등에 대해 배운다."),
        SubjectInfo("알고리즘해석및설계", "2학년", "문제를 효율적으로 해결하는 방법을 배웁니다. 새로운 문제를 풀 때 접근하는 방법, 어떤 방법이 시간/공간 면에서 효율적일지 등에 대해 배운다."),
        SubjectInfo("Adventure Design", "2학년", "디자인씽킹 프로세스를 근간으로 문제를 정의하고 SW 또는 서비스 설계 절차에 따라 디자인하고 설계하는 팀 프로젝트형 과목."),
        SubjectInfo("Ai입문", "2학년", "인공지능 관련 전공 심화 과목들을 수강하기 위해 알아야 할 인공지능 관련 주요 기초 개념을 습득하는 과목. 인공지능 신기술 및 오랜 주요 인공지능 기술들을 통섭한다."),
        SubjectInfo("임베디드SW", "2학년", "특정한 목적을 수행하는 임베디드 시스템에서 구동되는 SW에 관해 배우는 과목. 임베디드 SW 프로그래밍이 일반 프로그래밍과 다른 점을 배우고 실습을 통해 익힌다."),
        SubjectInfo("Ai프로그래밍", "2학년", "기초 파이썬 과목을 바탕으로 그 뒤에 파이썬의 여러 라이브러리를 배우 는 과목. 파이썬의 객체지향프로그램의 기초, 인공신경망을 위한 코딩과 수학의 기초를 배운다."),
        SubjectInfo("시스템 프로그래밍", "2학년", "프로그램을 컴퓨터가 어떻게 이해하는 지를 어셈블리어의 관점에서 공부하는 과목. 어셈블리어는 기계가 이해하는 기계어와 1:1 대응이 되는 언어로서, 학생들로금 컴퓨터를 잘 이해할 수 있는 기회를 제공한다."),
        SubjectInfo("산학 프로젝트", "3학년", "SW 개발 프로젝트 실무능력을 제고하기 위해 팀을 구성하여 실제로 SW 개발 프로젝트를 구현하는 과목. Cloud 및 AI 중심으로 기업에서 개발 경험이 풍부한 멘토들의 특강과 멘토링이 주기적으로 제공된다."),
        SubjectInfo("데이터베이스 기초", "3학년", "정보시스템의 중추적인 기능을 담당하는 데이터 베이스 시스템의 개념과 응용에 대해 배우는 과목. 표준 데이터 언어인 SQL을 습득하여 DBMS 활용 능력을 갖출 수 있다."),
        SubjectInfo("데이터사이언스 응용", "3학년", "데이터사이언스 기초 과목의 후속 과목. 데이터사이언스에서 배웠던 기술을 실제 응용해 보는 기회를 가진다."),
        SubjectInfo("웹SW스튜디오 및 재능기부", "3학년", "웹 프로그래밍 최신 기술을 중심으로 이론 및 실습 위주의 과목. 실무 위주로 진행되며, PC 웹페이지 작성 및 휴대 단말기용 앱 작성을 위한 프로그래밍 능력을 습득할 수 있다."),
        SubjectInfo("IoT", "3학년", "기본적인 센서 구동, 통신 방법과 데이터를 수집하고 처리하는 클라우드 사용법 을 배우고 실습을 통해 구현해 보는 과목. 우리 삶에 필요한 서비스를 프로젝트로 구현한다."),
        SubjectInfo("정보보호", "3학년", "정보보호 기술들에 대해 공부하는 과목. 블록체인 기술에 대한 기초 기술로서 이해가 필요한 암호기술 및 시스템 보안기술에 대해 공부하고, 실습을 통해 기술에 대한 이해를 높인다."),
        SubjectInfo("인공지능플래닝", "4학년", "Knowledge Reasoning and Planning에 대해 배우는 과목.지속적으로 Planning을 수립하고 이를 Action으로 실행하고, 실 수행 환경과의 상호 작용을 통해 Autonomous Agent를 구축하는 문제에 대해 배운다.")
    )

    lateinit var binding : FragmentSubjectRecyclerviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubjectRecyclerviewBinding.inflate(inflater, container, false)

        binding.recSubjects.layoutManager = LinearLayoutManager(context)
        binding.recSubjects.adapter = SubjectsAdapter(subjects)
        return binding.root
    }
}