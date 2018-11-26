package com.github.carniwar.springboot.scalable.core.service;

import com.github.carniwar.springboot.scalable.api.dto.DiffResultDTO;
import com.github.carniwar.springboot.scalable.api.enumeration.MessageCode;
import com.github.carniwar.springboot.scalable.api.exception.BusinessException;
import com.github.carniwar.springboot.scalable.core.domain.Diff;
import com.github.carniwar.springboot.scalable.core.repository.DiffRepository;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

import static com.github.carniwar.springboot.scalable.core.util.ValidationConstraints.*;

/**
 * Business object responsible for initialize transactions, validate business rules (@see <code>BusinessException</code>)
 * and perform persistent operations over repository classes.
 */
@Service
@Validated
public class DiffService {

    private static final Base64 BASE_64 = new Base64();

    private DiffRepository diffRepository;

    public DiffService(DiffRepository diffRepository) {
        this.diffRepository = diffRepository;
    }

    /**
     * Returns a <code>Diff</code> by its ID or <code>null</code> if it's not on the database.
     *
     * @param id - @NotBlank
     * @return Diff
     */
    public Diff findById(@NotBlank String id) {
        Optional<Diff> diff = diffRepository.findById(id);
        return diff.isPresent() ? diff.get() : null;
    }

    /**
     * Insert a new <code>Diff</code> with the left data set or update and existing one by its ID.
     * Checks if the data is a BASE64.
     *
     * @param id - @NotBlank
     * @param data - @NotBlank
     * @return <code>Diff</code>
     */
    @NotNull
    @Transactional
    public Diff saveLeft(@NotBlank @Size(min = DIFF_ID_SIZE_MIN, max = DIFF_ID_SIZE_MAX) String id,
                         @NotBlank @Size(min = DIFF_DATA_SIZE_MIN, max = DIFF_DATA_SIZE_MAX) String data) {
        Diff diff = findOrInit(id);
        if (!BASE_64.isInAlphabet(data))
            throw new BusinessException(MessageCode.DATA_NOT_BASE64, id);
        diff.setLeft(data);
        return diffRepository.save(diff);
    }

    /**
     * Insert a new <code>Diff</code> with the right data set or update and existing one by its ID.
     * Checks if the data is a BASE64.
     *
     * @param id - @NotBlank
     * @param data - @NotBlank
     * @return <code>Diff</code>
     */
    @NotNull
    @Transactional
    public Diff saveRight(@NotBlank @Size(min = DIFF_ID_SIZE_MIN, max = DIFF_ID_SIZE_MAX) String id,
                          @NotBlank @Size(min = DIFF_DATA_SIZE_MIN, max = DIFF_DATA_SIZE_MAX) String data) {
        Diff diff = findOrInit(id);
        if (!BASE_64.isInAlphabet(data))
            throw new BusinessException(MessageCode.DATA_NOT_BASE64, id);
        diff.setRight(data);
        return diffRepository.save(diff);
    }

    /**
     * Retrieve information about a <code>Diff</code> and calculates some metadata about left and right data.
     *
     * @param id - @NotBlank
     * @return <code>DiffResultDTO</code>
     */
    @NotNull
    public DiffResultDTO compare(@NotBlank String id) {
        Optional<Diff> diffOptional = diffRepository.findById(id);

        if (!diffOptional.isPresent())
            throw new BusinessException(MessageCode.DIFF_DATA_NOT_FOUND, id);

        Diff diff = diffOptional.get();
        if (StringUtils.isBlank(diff.getLeft()))
            throw new BusinessException(MessageCode.DIFF_LEFT_DATA_BLANK, id);
        if (StringUtils.isBlank(diff.getRight()))
            throw new BusinessException(MessageCode.DIFF_RIGHT_DATA_BLANK, id);

        return new DiffResultDTO(id,
                diff.getLeft().equals(diff.getRight()),
                diff.getLeft().length() == diff.getRight().length(),
                StringUtils.indexOfDifference(diff.getLeft(), diff.getRight()));
    }

    /*
     * Private Methods
     */

    private Diff findOrInit(String id) {
        Diff diff = findById(id);
        return diff == null ? new Diff(id) : diff;
    }

}
