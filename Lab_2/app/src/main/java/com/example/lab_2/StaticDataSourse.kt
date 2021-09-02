package com.example.lab_2

class StaticDataSourse() {
    companion object{
        fun getDepartment(): Department{
            val department:Department = Department(getGroup())
            return department
        }
        fun getGroup(): List<Group>{
            val group:List<Group> =  listOf(
                Group(301, listOf(
                    Student("Yehor_301"),
                    Student("Kolya"),
                    Student("Ihor"))),
                Group(302, listOf(
                    Student("Masha_302"),
                    Student("Petya"),
                    Student("Natasha"))),
                Group(303, listOf(
                    Student("Omelko_303"),
                    Student("Maksym"),
                    Student("Petro"))),
                Group(304, listOf(
                    Student("Ibrahim_304"),
                    Student("Mia"),
                    Student("Nazar")))
            )
            return group;
        }

    }

}