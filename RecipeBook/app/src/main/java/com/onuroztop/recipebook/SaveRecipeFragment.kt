package com.onuroztop.recipebook

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_save_recipe.*
import java.io.ByteArrayOutputStream
import kotlin.random.Random


class SaveRecipeFragment : Fragment() {

    var selectedImageURI : Uri? = null
    var selectedImgBitmap : Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clearButton.setOnClickListener {

            clearFields(it)

        }

        saveButton.setOnClickListener {

            saveRecipe(it)

        }

        recipeImage.setOnClickListener {

            chooseImage(it)

        }

        arguments?.let {

            val fromWhere = SaveRecipeFragmentArgs.fromBundle(it).fromWhere
            val selectedItemID = SaveRecipeFragmentArgs.fromBundle(it).selectedItemID

            if(fromWhere == "menu"){

                editTextRecipeName.setText("")
                editTextIngredients.setText("")
                saveButton.visibility = View.VISIBLE
                clearButton.visibility = View.VISIBLE

                val chooseImageBitmap = BitmapFactory.decodeResource(requireContext().resources,R.drawable.add_image)
                recipeImage.setImageBitmap(chooseImageBitmap)

            }else{

                saveButton.visibility = View.INVISIBLE
                clearButton.visibility = View.INVISIBLE

                try {

                    val database = requireContext().applicationContext.openOrCreateDatabase("RecipeBookDatabase",Context.MODE_PRIVATE,null)
                    val cursor = database.rawQuery("SELECT * FROM recipes WHERE id = ?", arrayOf(selectedItemID.toString()))

                    val nameColumnIndex = cursor.getColumnIndex("name")
                    val ingredientsColumnIndex = cursor.getColumnIndex("ingredients")
                    val imageColumnIndex = cursor.getColumnIndex("img")

                    while (cursor.moveToNext()){

                        editTextRecipeName.setText(cursor.getString(nameColumnIndex))
                        editTextIngredients.setText(cursor.getString(ingredientsColumnIndex))

                        val imgByteArray = cursor.getBlob(imageColumnIndex)
                        val bitmap = BitmapFactory.decodeByteArray(imgByteArray,0,imgByteArray.size)
                        recipeImage.setImageBitmap(bitmap)

                    }



                    cursor.close()

                }catch (e:Exception){

                    println(e.message)

                }



            }

        }


    }

    private fun saveRecipe(view:View){

        val recipeTitle = editTextRecipeName.text.toString()
        val ingredients = editTextIngredients.text.toString()

        if (selectedImgBitmap != null){

            val compressedBitmap = compressBitmap(selectedImgBitmap!!,300)

            val outputStream = ByteArrayOutputStream()
            compressedBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteArrayOfBitmap = outputStream.toByteArray()


            try{

                val database = requireContext().applicationContext.openOrCreateDatabase("RecipeBookDatabase",Context.MODE_PRIVATE,null)

                //create table
                database.execSQL("CREATE TABLE IF NOT EXISTS recipes(id INTEGER PRIMARY KEY ASC AUTOINCREMENT,name TEXT,ingredients TEXT,img BLOB)")

                //prepared statements for insert op.
                var insertQuery = "INSERT INTO recipes(name,ingredients,img) VALUES(?,?,?)"
                var statement = database.compileStatement(insertQuery)
                statement.bindString(1,recipeTitle)
                statement.bindString(2,ingredients)
                statement.bindBlob(3,byteArrayOfBitmap)
                statement.execute()


            }catch (e:Exception){

                println(e.message)

            }



            val action = SaveRecipeFragmentDirections.actionSaveRecipeFragmentToRecipeListFragment()
            val navigator = Navigation.findNavController(requireView())
            navigator.navigate(action)



        }


    }

    private fun clearFields(view:View){



    }



    private fun chooseImage(view: View){

        activity?.let {

            if(ContextCompat.checkSelfPermission(it.applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                //need permission
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)

            }else{

                //permission is granted

                val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent,2)
            }

        }

    }


    private fun compressBitmap(bitmap:Bitmap,maxDataSize:Int) : Bitmap{

        var w = bitmap.width
        var h = bitmap.height

        val rate:Double = w.toDouble() / h.toDouble()

        if(rate > 1){
            //horizontal image

            w = maxDataSize
            val newH = w / rate
            h = newH.toInt()


        }else{
            //vertical image

            h = maxDataSize
            val newW = h * rate
            w = newW.toInt()

        }


        return Bitmap.createScaledBitmap(bitmap,w,h,true)

    }


    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 1){

            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //permission is granted

                val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent,2)

            }

        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){

            selectedImageURI = data.data

            try {

                selectedImageURI?.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                        val source =  ImageDecoder.createSource(requireContext().applicationContext.contentResolver, it)

                        selectedImgBitmap = ImageDecoder.decodeBitmap(source)
                        recipeImage.setImageBitmap(selectedImgBitmap)

                    } else {

                        selectedImgBitmap = MediaStore.Images.Media.getBitmap(requireContext().applicationContext.contentResolver,selectedImageURI)
                        recipeImage.setImageBitmap(selectedImgBitmap)

                    }
                }


            }catch (e:Exception){

                println(e.message)

            }


        }


    }


}