package jp.ac.ohara.schedule.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.schedule.model.UserBook;
import jp.ac.ohara.schedule.repository.UserBookRepository;
 
@Service
@Transactional
public class UserBookService {
 
	@Autowired
	private UserBookRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
 
	/**
	 * ユーザー一覧の取得
	 * @return List<UserBook>
	 */
	public List<UserBook> getUserList() {
	    List<UserBook> entityList = this.repository.findAll();
	    return entityList;
	}
 
	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  UserBook
	 */
	public UserBook get(@NonNull Long index) {
		UserBook userBook = this.repository.findById(index).orElse(new UserBook());
		return userBook;
	}
 
	/**
	 * ユーザーデータの保存
	 * @param UserBook userBook
	 */
	public void save(@NonNull UserBook userBook) {
		userBook.setPassword(this.passwordEncoder.encode(userBook.getPassword()));
		this.repository.save(userBook);
	}
 
	/**
	 * ユーザーデータの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		this.repository.deleteById(index);
	}
 
	
}