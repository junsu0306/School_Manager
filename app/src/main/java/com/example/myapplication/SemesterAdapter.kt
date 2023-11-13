// SemesterAdapter.kt

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemSemesterBinding

class SemesterAdapter(
    private val semesterList: List<String>,
    private val onItemSelectedListener: (String) -> Unit
) : RecyclerView.Adapter<SemesterAdapter.SemesterViewHolder>(), SpinnerAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemesterViewHolder {
        val binding = ItemSemesterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SemesterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SemesterViewHolder, position: Int) {
        val semester = semesterList[position]
        holder.bind(semester)
    }

    override fun getItemCount(): Int = semesterList.size

    inner class SemesterViewHolder(private val binding: ItemSemesterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(semester: String) {
            binding.txtSemester.text = semester

            binding.root.setOnClickListener {
                onItemSelectedListener.invoke(semester)
            }
        }
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}



