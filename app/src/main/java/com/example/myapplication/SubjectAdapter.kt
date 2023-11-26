import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Subject
import com.example.myapplication.databinding.ItemSubjectBinding

class  SubjectAdapter : ListAdapter<Subject, SubjectAdapter.SubjectViewHolder>(SubjectDiffCallback()) {

    // ViewHolder 클래스 정의

    class SubjectViewHolder private constructor(private val binding: ItemSubjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Subject) {
            binding.txtCoursename.text = item.courseName
            binding.txtGrade.text = item.grade.toString()
            binding.txtCredit.text = item.credits.toString()
            binding.txtMajor.text = item.major.toString()
        }

        companion object {
            fun from(parent: ViewGroup): SubjectViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSubjectBinding.inflate(layoutInflater, parent, false)
                return SubjectViewHolder(binding)
            }
        }
    }

    // DiffCallback 클래스 정의

    private class SubjectDiffCallback : DiffUtil.ItemCallback<Subject>() {
        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val currentSubject = getItem(position)
        holder.bind(currentSubject)
    }
}

