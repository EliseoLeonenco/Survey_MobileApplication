package com.example.dmusurveycoursework.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

/* Database Config*/
private val DataBaseName = "SurveyDataBase.db"
private val ver: Int = 1

class SurveyDataBase(context: Context) : SQLiteOpenHelper(context, DataBaseName, null, ver) {

    /* Student Table */
    public val StudentTableName = "Student"
    public val StudentColumn_ID = "ID"
    public val Column_StudentFirstName = "FirstName"
    public val Column_StudentLastName = "LastName"
    public val Column_StudentUserName = "UserName"
    public val Column_StudentPassword = "Password"

    /*************************/

    /* Admin Table */
    public val AdminTableName = "Admin"
    public val AdminColumn_ID = "ID"
    public val Column_AdminFirstName = "FirstName"
    public val Column_AdminLastName = "LastName"
    public val Column_AdminUserName = "UserName"
    public val Column_AdminPassword = "Password"

    /*************************/

    /* Survey Table */
    public val SurveyTableName = "Survey"
    public val SurveyColumn_ID = "ID"
    public val Column_SurveyTitle = "Title"

    /*************************/

    /* Question Table */
    public val QuestionTableName = "Question"
    public val QuestionColumn_ID = "ID"
    public val Column_QuestionSurveyID = "SurveyID"
    public val Column_QuestionText = "QuestionText"

    /*************************/

    /* Answer Table */
    public val AnswerTableName = "Answer"
    public val AnswerColumn_ID = "ID"
    public val Column_AnswerText = "AnswerText"

    /*************************/

    /* PublishedSurvey Table */
    public val PublishedSurveyTableName = "PublishedSurvey"
    public val PublishedSurveyColumn_ID = "ID"
    public val Column_PublishedSurveySurveyID = "SurveyID"
    public val Column_StartDate = "StartDate"
    public val Column_EndDate = "EndDate"

    /*************************/

    /* StudentSurveyRespond Table */
    public val StudentSurveyRespondTableName = "StudentSurveyRespond"
    public val StudentSurveyRespondColumn_ID = "ID"
    public val Column_StudentSurveyRespondStudentID = "StudentID"
    public val Column_StudentSurveyRespondPublishedSurveyID = "PublishedSurveyID"
    public val Column_StudentSurveyRespondQuestionID = "QuestionID"
    public val Column_StudentSurveyRespondAnswerID = "AnswerID"

    /*************************/

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            /* CreateStatement for StudentTable */
            var sqlCreateStatement: String =
                "CREATE TABLE " + StudentTableName + " ( " + StudentColumn_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_StudentFirstName + " TEXT NOT NULL, " +
                        Column_StudentLastName + " TEXT NOT NULL, " + Column_StudentUserName +
                        " TEXT NOT NULL UNIQUE, " + Column_StudentPassword + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            /* CreateStatement for AdminTable */
            sqlCreateStatement = "CREATE TABLE " + AdminTableName + " ( " + AdminColumn_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_AdminFirstName + " TEXT NOT NULL, " +
                    Column_AdminLastName + " TEXT NOT NULL, " + Column_AdminUserName +
                    " TEXT NOT NULL UNIQUE, " + Column_AdminPassword + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            /* CreateStatement for SurveyTable */
            sqlCreateStatement = "CREATE TABLE " + SurveyTableName + " ( " + SurveyColumn_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_SurveyTitle + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            /* CreateStatement for QuestionTable */
            sqlCreateStatement = "CREATE TABLE " + QuestionTableName + " ( " + QuestionColumn_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_QuestionSurveyID + " TEXT NOT NULL, " +
                    Column_QuestionText + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            /* CreateStatement for AnswerTable */
            sqlCreateStatement = "CREATE TABLE " + AnswerTableName + " ( " + AnswerColumn_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_AnswerText + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            /* CreateStatement for PublishedSurveyTable */
            sqlCreateStatement =
                "CREATE TABLE " + PublishedSurveyTableName + " ( " + PublishedSurveyColumn_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_PublishedSurveySurveyID + " TEXT NOT NULL, " +
                        Column_StartDate + " TEXT NOT NULL, " + Column_EndDate + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)


            /* CreateStatement for StudentSurveyRespondTable */
            sqlCreateStatement =
                "CREATE TABLE " + StudentSurveyRespondTableName + " ( " + StudentSurveyRespondColumn_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_StudentSurveyRespondStudentID + " TEXT NOT NULL, " +
                        Column_StudentSurveyRespondPublishedSurveyID + " TEXT NOT NULL, " + Column_StudentSurveyRespondQuestionID +
                        " TEXT NOT NULL, " + Column_StudentSurveyRespondAnswerID + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)
        } catch (e: SQLiteException) {
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    /**
     * return  1 : the new user has been add to the database successfully
     * return -1 : Error, adding new user
     * return -2 : can not open database
     * return -3 : user name is already exist
     *
     */
    fun addStudent(student: Student): Int {

        val isUserNameAlreadyExists =
            checkStudentUserName(student) // check if the username is already exist in the database
        if (isUserNameAlreadyExists < 0)
            return isUserNameAlreadyExists

        // writableDatabase for insert actions
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_StudentFirstName, student.firstName)
        cv.put(Column_StudentLastName, student.lastName)
        cv.put(Column_StudentUserName, student.userName.lowercase())
        cv.put(Column_StudentPassword, student.password)

        val success = db.insert(StudentTableName, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return 1

    }

    private fun checkStudentUserName(student: Student): Int {
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            return -2
        }

        val userName = student.userName.lowercase()

        val sqlStatement = "SELECT * FROM $StudentTableName WHERE $Column_StudentUserName = ?"
        val param = arrayOf(userName)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if (cursor.moveToFirst()) {
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3 // error the user name is already exist
        }
        cursor.close()
        db.close()
        return 0 //User not found
    }

    fun getStudent(student: Student): Int {
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            return -2
        }

        val userName = student.userName.lowercase()
        val userPassword = student.password

        val sqlStatement =
            "SELECT * FROM $StudentTableName WHERE $Column_StudentUserName = ? AND $Column_StudentPassword = ?"
        val param = arrayOf(userName, userPassword)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if (cursor.moveToFirst()) {
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }
        cursor.close()
        db.close()
        return -1 //User not found
    }

    fun getStudentInfo(student: Student): Student {
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            return student
        }

        val userName = student.userName.lowercase()
        val userPassword = student.password

        val sqlStatement =
            "SELECT * FROM $StudentTableName WHERE $Column_StudentUserName = ? AND $Column_StudentPassword = ?"
        val param = arrayOf(userName, userPassword)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if (cursor.moveToFirst()) {
            student.id = cursor.getInt(0)
            student.firstName = cursor.getString(1)
            student.lastName = cursor.getString(2)
            student.userName = cursor.getString(3)
            student.password = cursor.getString(4)
        }
        return student
    }

    fun getAdmin(admin: Admin): Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            return -2
        }

        val userName = admin.userName.lowercase()
        val userPassword = admin.password

        val sqlStatement =
            "SELECT * FROM $AdminTableName WHERE $Column_AdminUserName = ? AND $Column_AdminPassword = ?"
        val param = arrayOf(userName, userPassword)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if (cursor.moveToFirst()) {
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }
        cursor.close()
        db.close()
        return -1 //User not found
    }

    fun getAdminInfo(admin: Admin): Admin {
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            return admin
        }

        val userName = admin.userName.lowercase()
        val userPassword = admin.password

        val sqlStatement =
            "SELECT * FROM $AdminTableName WHERE $Column_AdminUserName = ? AND $Column_AdminPassword = ?"
        val param = arrayOf(userName, userPassword)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if (cursor.moveToFirst()) {
            admin.id = cursor.getInt(0)
            admin.firstName = cursor.getString(1)
            admin.lastName = cursor.getString(2)
            admin.userName = cursor.getString(3)
            admin.password = cursor.getString(4)
        }
        return admin
    }

    fun getAllQuestions(surveyID: Int): ArrayList<Question> {

        val questionList = ArrayList<Question>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement =
            "SELECT * FROM $QuestionTableName WHERE $Column_QuestionSurveyID = $surveyID"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val questionId: Int = cursor.getInt(0)
                val surveyId: Int = cursor.getInt(1)
                val questionText: String = cursor.getString(2)
                val b = Question(questionId, surveyId, questionText)
                questionList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return questionList
    }


    fun getAllSurveys(): ArrayList<Survey> {
        val surveyList = ArrayList<Survey>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement =
            "SELECT $SurveyTableName.$SurveyColumn_ID, $SurveyTableName.$Column_SurveyTitle, $PublishedSurveyTableName.$Column_StartDate, $PublishedSurveyTableName.$Column_EndDate FROM $SurveyTableName, $PublishedSurveyTableName WHERE $SurveyTableName.$SurveyColumn_ID = $PublishedSurveyTableName.$Column_PublishedSurveySurveyID"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val surveyId: Int = cursor.getInt(0)
                val surveyTitle: String = cursor.getString(1)
                val startDate: String = cursor.getString(2)
                val endDate: String = cursor.getString(3)
                val b = Survey(surveyId, surveyTitle, startDate, endDate)
                surveyList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return surveyList
    }

    fun addSurveyDates(surveyID: Int, startDate: String, endDate: String) {
        // writableDatabase for insert actions
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_PublishedSurveySurveyID, surveyID)
        cv.put(Column_StartDate, startDate)
        cv.put(Column_EndDate, endDate)

        db.insert(PublishedSurveyTableName, null, cv)

        db.close()
    }

    fun addSurveyQuestions(question: String, surveyID: Int) {
        // writableDatabase for insert actions
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_QuestionText, question)
        cv.put(Column_QuestionSurveyID, surveyID)

        db.insert(QuestionTableName, null, cv)

        db.close()
    }


    fun getLastSurveyID(): Int {
        var lastID: Int = 1
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $SurveyTableName ORDER BY $SurveyColumn_ID DESC LIMIT 1;"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst()) {
            lastID = cursor.getInt(0)
        }

        return lastID
    }

    fun addSurveyTitle(surveyTitle: String): Int {

        val isUserNameAlreadyExists =
            checkSurveyTitle(surveyTitle) // check if the username is already exist in the database
        if (isUserNameAlreadyExists < 0)
            return isUserNameAlreadyExists

        // writableDatabase for insert actions
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_SurveyTitle, surveyTitle.uppercase())

        val success = db.insert(SurveyTableName, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return 1
    }

    private fun checkSurveyTitle(surveyTitle: String): Int {
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            return -2
        }

        val surveyTitleUpper = surveyTitle.uppercase()

        val sqlStatement = "SELECT * FROM $SurveyTableName WHERE $Column_SurveyTitle = ?"
        val param = arrayOf(surveyTitleUpper)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if (cursor.moveToFirst()) {
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3 // error the user name is already exist
        }
        cursor.close()
        db.close()
        return 0 //User not found
    }

    fun addAnswerToDatabase(
        studentID: Int,
        publishedSurveyID: Int,
        questionID: Int,
        answerID: Int
    ) {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_StudentSurveyRespondStudentID, studentID)
        cv.put(Column_StudentSurveyRespondPublishedSurveyID, publishedSurveyID)
        cv.put(Column_StudentSurveyRespondQuestionID, questionID)
        cv.put(Column_StudentSurveyRespondAnswerID, answerID)

        db.insert(StudentSurveyRespondTableName, null, cv)
        db.close()
    }

    fun checkIfSurveyHasBeenAnswered(userID: Int, publishedSurveyID: Int): Int {
        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            return -2
        }

        val userId = userID
        val survID = publishedSurveyID

        val sqlStatement =
            "SELECT * FROM $StudentSurveyRespondTableName WHERE $Column_StudentSurveyRespondStudentID = $userID AND $Column_StudentSurveyRespondPublishedSurveyID = $publishedSurveyID"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst()) {
            // The user is found
            val n = cursor.getInt(0)
            return 1 // Survey has been answered by user
            cursor.close()
            db.close()
        }
        cursor.close()
        db.close()
        return 0 // Has not been answered
    }

    fun getSurveyRespondCountByID(publishedSurveyID: Int): Int {
        val db: SQLiteDatabase = this.readableDatabase
        val surveyID = publishedSurveyID

        val sqlStatement =
            "SELECT COUNT(PublishedSurveyID) FROM $StudentSurveyRespondTableName WHERE $Column_StudentSurveyRespondPublishedSurveyID = $surveyID"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst()) {
            var count = cursor.getInt(0)
            count = count / 10
            return count
            cursor.close()
            db.close()
        }
        cursor.close()
        db.close()
        return 0
    }

    fun getSurveyResponsesByID(publishedSurveyID: Int): ArrayList<StudentSurveyRespond> {
        val db: SQLiteDatabase = this.readableDatabase
        val surveyID = publishedSurveyID
        val studentSurveyRespondList = ArrayList<StudentSurveyRespond>()

        val sqlStatement =
            "SELECT * FROM $StudentSurveyRespondTableName WHERE $Column_StudentSurveyRespondPublishedSurveyID = $publishedSurveyID"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(0)
                var studentID = cursor.getInt(1)
                var publishedSurveyID = cursor.getInt(2)
                var questionID = cursor.getInt(3)
                var answerID = cursor.getInt(4)
                val b = StudentSurveyRespond(id, studentID, publishedSurveyID, questionID, answerID)
                studentSurveyRespondList.add(b)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return studentSurveyRespondList
    }

    fun updateSurveyEndDateByID(surveyID: Int, endDate: String): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_EndDate, endDate)

        val result = db.update(
            PublishedSurveyTableName,
            cv,
            "$Column_PublishedSurveySurveyID =  $surveyID",
            null
        ) == 1
        db.close()
        return result
    }

    fun updateSurveyQuestionByID(questionID: Int, questionText: String): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_QuestionText, questionText)

        val result =
            db.update(QuestionTableName, cv, "$QuestionColumn_ID =  $questionID", null) == 1
        db.close()
        return result
    }

}